package com.misenvios.misenviosapi.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipment_status")
@Builder
public class ShipmentStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private String plant;
    private String history;
    private String status;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;
}