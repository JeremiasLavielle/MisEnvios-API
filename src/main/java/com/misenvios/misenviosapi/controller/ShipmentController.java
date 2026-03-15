package com.misenvios.misenviosapi.controller;


import com.misenvios.misenviosapi.dto.ShipmentDetailDTO;
import com.misenvios.misenviosapi.dto.ShipmentRequestDTO;
import com.misenvios.misenviosapi.dto.ShipmentStatusDTO;
import com.misenvios.misenviosapi.dto.ShipmentSummaryDTO;
import com.misenvios.misenviosapi.service.ICourierService;
import com.misenvios.misenviosapi.service.IShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

public class ShipmentController {

    @Autowired
    private IShipmentService shipmentService;

    @PostMapping("/{userId}/shipments")
    public ResponseEntity<ShipmentSummaryDTO> newShipment(@PathVariable Long userId, @RequestBody ShipmentRequestDTO dto){
        ShipmentSummaryDTO response = shipmentService.newShipment(userId, dto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{userId}/shipments")
    public ResponseEntity<List<ShipmentSummaryDTO>> getShipments(@PathVariable Long userId) throws Exception {
        List<ShipmentSummaryDTO> response = shipmentService.getShipments(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/shipments/{shipmentId}/refresh")
    public ResponseEntity<ShipmentSummaryDTO>  getShipment(@PathVariable Long shipmentId) throws Exception {
        ShipmentSummaryDTO response = shipmentService.getShipment(shipmentId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/shipments/{id}")
    public ResponseEntity<ShipmentDetailDTO> getShipmentDetail(@PathVariable Long id) {
        ShipmentDetailDTO response = shipmentService.getShipmentDetail(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/shipments/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id){
        shipmentService.deleteShipment(id);
        return ResponseEntity.noContent().build();
    }

}
