package com.misenvios.misenviosapi.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ShipmentDetailDTO {
    private Long id;
    private String name;
    private String trackingCode;
    private String lastStatus;
    private LocalDateTime lastUpdatedAt;
    private List<ShipmentStatusDTO> movements;
}
