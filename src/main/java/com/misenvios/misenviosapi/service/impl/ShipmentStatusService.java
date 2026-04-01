package com.misenvios.misenviosapi.service.impl;

import com.misenvios.misenviosapi.dto.ShipmentStatusDTO;
import com.misenvios.misenviosapi.mapper.ShipmentStatusMapper;
import com.misenvios.misenviosapi.model.Shipment;
import com.misenvios.misenviosapi.model.ShipmentStatus;
import com.misenvios.misenviosapi.repository.ShipmentRepository;
import com.misenvios.misenviosapi.repository.ShipmentStatusRepository;
import com.misenvios.misenviosapi.service.ICourierService;
import com.misenvios.misenviosapi.service.IShipmentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class ShipmentStatusService implements IShipmentStatusService {

    @Autowired
    private ShipmentStatusRepository shipmentStatusRepository;
    @Autowired
    private Map<String, ICourierService> courierServices;
    @Autowired
    private ShipmentRepository shipmentRepository;

    public Shipment updateShipmentStatus(Shipment shipment) throws Exception {
        List<ShipmentStatusDTO> statuses = fetchStatusesSortedByDateDesc(shipment);

        persistStatuses(statuses, shipment);
        updateShipmentLastStatus(shipment, statuses);

        return shipmentRepository.save(shipment);
    }

    private List<ShipmentStatusDTO> fetchStatusesSortedByDateDesc(Shipment shipment) throws Exception {
        return courierServices.get(shipment.getCourier().name())
                .getShipmentStatus(shipment.getTrackingCode())
                .stream()
                .sorted(Comparator.comparing(ShipmentStatusDTO::getDate).reversed())
                .toList();
    }

    private void persistStatuses(List<ShipmentStatusDTO> statuses, Shipment shipment) {
        shipmentStatusRepository.deleteByShipmentId(shipment.getId());
        List<ShipmentStatus> entities = statuses.stream()
                .map(dto -> ShipmentStatusMapper.toEntity(dto, shipment))
                .toList();
        shipmentStatusRepository.saveAll(entities);
    }

    private void updateShipmentLastStatus(Shipment shipment, List<ShipmentStatusDTO> statuses) {
        if(statuses == null || statuses.isEmpty()) return;
        ShipmentStatusDTO latest = statuses.getFirst();

        if (latest.getStatus() != null && !latest.getStatus().isBlank()){
            shipment.setLastStatus(latest.getStatus());
        } else {
            shipment.setLastStatus(latest.getHistory());
        }
        shipment.setLastUpdatedAt(latest.getDate());
        shipment.setLastPlant(latest.getPlant());
    }

    @Override
    public List<ShipmentStatusDTO> getShipmentStatus(Long id) {
        return List.of();
    }

}
