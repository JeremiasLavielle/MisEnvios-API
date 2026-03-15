package com.misenvios.misenviosapi.model;

import com.misenvios.misenviosapi.service.impl.CourierResolverService;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipments")
@Builder

public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String trackingCode;
    private String name;
    private String lastStatus;
    private LocalDateTime lastUpdatedAt;
    private Courier courier;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}