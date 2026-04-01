package com.misenvios.misenviosapi.mapper;

import com.misenvios.misenviosapi.dto.ShipmentDetailDTO;
import com.misenvios.misenviosapi.dto.ShipmentRequestDTO;
import com.misenvios.misenviosapi.dto.ShipmentStatusDTO;
import com.misenvios.misenviosapi.dto.ShipmentSummaryDTO;
import com.misenvios.misenviosapi.model.Shipment;
import com.misenvios.misenviosapi.model.User;

import java.util.List;

public class ShipmentMapper {

    public static Shipment toEntity(ShipmentRequestDTO dto, User user) {
        if (dto == null) return null;
        return Shipment.builder()
                .trackingCode(dto.getTrackingCode())
                .name(dto.getName())
                .active(true)
                .user(user)
                .build();
    }

    public static ShipmentSummaryDTO toSummaryDTO(Shipment s) {
        if (s == null) return null;
        return ShipmentSummaryDTO.builder()
                .id(s.getId())
                .name(s.getName())
                .lastStatus(s.getLastStatus())
                .lastUpdatedAt(s.getLastUpdatedAt())
                .lastPlant(s.getLastPlant())
                .build();
    }

    public static ShipmentDetailDTO toDetailDTO(Shipment s, List<ShipmentStatusDTO> movements) {
        if (s == null) return null;
        return ShipmentDetailDTO.builder()
                .id(s.getId())
                .name(s.getName())
                .trackingCode(s.getTrackingCode())
                .lastStatus(s.getLastStatus())
                .lastUpdatedAt(s.getLastUpdatedAt())
                .movements(movements)
                .build();
    }
}