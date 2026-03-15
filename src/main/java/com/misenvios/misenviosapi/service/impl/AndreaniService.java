package com.misenvios.misenviosapi.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.misenvios.misenviosapi.util.CryptoUtil;
import com.misenvios.misenviosapi.dto.ShipmentStatusDTO;
import com.misenvios.misenviosapi.service.ICourierService;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service("ANDREANI")
public class AndreaniService implements ICourierService {

    private static final Logger log = LoggerFactory.getLogger(AndreaniService.class);
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public AndreaniService(WebClient andreaniWebClient, ObjectMapper objectMapper) {
        this.webClient = andreaniWebClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<ShipmentStatusDTO> getShipmentStatus(String trackingCode) throws Exception {

        String encrypted = CryptoUtil.encryptTracking(trackingCode);
        log.info("Número: {} → payload: {}", trackingCode, encrypted);

        String json;
        try {
            json = webClient.get()
                    .uri(u -> u
                            .path("v3/Tracking")
                            .queryParam("payload", encrypted)
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            log.info("Tracking obtenido correctamente");
        } catch (WebClientResponseException e) {
            log.error("Error {} → {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw new Exception("Error al consultar Andreani: " + e.getStatusCode());
        } catch (Exception e) {
            log.error("Error inesperado", e);
            throw new Exception("Error inesperado al consultar Andreani: " + e.getMessage());
        }

        List<ShipmentStatusDTO> estados = new ArrayList<>();
        JsonNode root = objectMapper.readTree(json);
        JsonNode timelines = root.get("timelines");

        if (timelines == null || !timelines.isArray()) {
            return estados;
        }

        for (JsonNode timeline : timelines) {
            String titulo = timeline.get("titulo").asText();
            JsonNode traducciones = timeline.get("traducciones");

            if (traducciones == null || !traducciones.isArray()) {
                continue;
            }

            for (JsonNode traduccion : traducciones) {
                String descripcion = Jsoup.parse(traduccion.get("traduccion").asText()).text();
                String fechaTexto  = traduccion.get("fechaEvento").asText();
                String sucursal    = traduccion.path("sucursal").path("nombre").asText("");

                LocalDateTime fecha = LocalDateTime.parse(
                        fechaTexto.length() > 19 ? fechaTexto.substring(0, 19) : fechaTexto,
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME
                );

                ShipmentStatusDTO dto = new ShipmentStatusDTO();
                dto.setDate(fecha);
                dto.setStatus(titulo);
                dto.setHistory(descripcion);
                dto.setPlant(sucursal);

                estados.add(dto);
            }
        }

        return estados;
    }
}