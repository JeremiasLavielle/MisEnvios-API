package com.misenvios.misenviosapi.mapper;

import com.misenvios.misenviosapi.dto.ShipmentStatusDTO;
import com.misenvios.misenviosapi.model.Shipment;
import com.misenvios.misenviosapi.model.ShipmentStatus;

public class ShipmentStatusMapper {

    public static ShipmentStatusDTO toDTO(ShipmentStatus s) {
        if (s == null) return null;
        return ShipmentStatusDTO.builder()
                .date(s.getDate())
                .plant(s.getPlant())
                .history(s.getHistory())
                .status(s.getStatus())
                .build();
    }

    public static ShipmentStatus toEntity(ShipmentStatusDTO dto, Shipment shipment) {
        if (dto == null) return null;
        return ShipmentStatus.builder()
                .date(dto.getDate())
                .plant(dto.getPlant())
                .history(dto.getHistory())
                .status(dto.getStatus())
                .shipment(shipment)
                .build();
    }
}