package com.misenvios.misenviosapi.repository;

import com.misenvios.misenviosapi.model.Shipment;
import com.misenvios.misenviosapi.model.ShipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShipmentRepository extends JpaRepository <Shipment, Long> {
    List<Shipment> findByUserIdAndActiveTrue(Long userId);
    Optional<Shipment> findByTrackingCode(String trackingCode);
}
