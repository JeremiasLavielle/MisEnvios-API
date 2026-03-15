package com.misenvios.misenviosapi.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ShipmentSummaryDTO {
    private Long id;
    private String name;
    private String lastStatus;
    private LocalDateTime lastUpdatedAt;
}
