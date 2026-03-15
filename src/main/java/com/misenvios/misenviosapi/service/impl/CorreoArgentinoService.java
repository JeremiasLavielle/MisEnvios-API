package com.misenvios.misenviosapi.service.impl;

import com.misenvios.misenviosapi.dto.ShipmentStatusDTO;
import com.misenvios.misenviosapi.service.ICourierService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service("CORREO_ARGENTINO")
public class CorreoArgentinoService implements ICourierService {

    @Override
    public List<ShipmentStatusDTO> getShipmentStatus(String trackingCode) throws Exception {

        Document doc = Jsoup.connect("https://www.correoargentino.com.ar/sites/all/modules/custom/ca_forms/api/wsFacade.php")
                .method(Connection.Method.POST)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                .header("Referer", "https://www.correoargentino.com.ar/formularios/e-commerce")
                .data("action", "ecommerce")
                .data("id", trackingCode)
                .execute()
                .parse();

        Elements rows = doc.select("tr");
        if (!rows.isEmpty()) {
            rows.remove(0);
        }
        List<ShipmentStatusDTO> estados = new ArrayList<>();

        for (Element row : rows) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String fechaTexto = row.select("td[data-title=Fecha:]").text();
            LocalDateTime fecha = LocalDateTime.parse(fechaTexto, formatter);

            ShipmentStatusDTO dto = new ShipmentStatusDTO();
            dto.setDate(fecha);
            dto.setPlant(row.select("td[data-title=Planta:]").text());
            dto.setHistory(row.select("td[data-title=Historia:]").text());
            dto.setStatus(row.select("td[data-title=Estado:]").text());
            estados.add(dto);
        }

        return estados;
    }}