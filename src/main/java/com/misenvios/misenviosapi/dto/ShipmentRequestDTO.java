package com.misenvios.misenviosapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

public class ShipmentRequestDTO {
    private String trackingCode;
    private String name;
}
