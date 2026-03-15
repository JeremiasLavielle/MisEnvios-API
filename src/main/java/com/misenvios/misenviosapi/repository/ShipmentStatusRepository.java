package com.misenvios.misenviosapi.repository;

import com.misenvios.misenviosapi.model.ShipmentStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface ShipmentStatusRepository extends JpaRepository <ShipmentStatus, Long> {
    List<ShipmentStatus> findByShipmentId(Long shipmentId);
    void deleteByShipmentId(Long shipmentId);
}
