package com.misenvios.misenviosapi.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ShipmentStatusDTO {
    private LocalDateTime date;
    private String plant;
    private String history;
    private String status;
}
