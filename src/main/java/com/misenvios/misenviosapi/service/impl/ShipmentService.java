package com.misenvios.misenviosapi.service.impl;

import com.misenvios.misenviosapi.dto.*;
import com.misenvios.misenviosapi.exceptions.ShipmentAlreadyExistsException;
import com.misenvios.misenviosapi.mapper.ShipmentMapper;
import com.misenvios.misenviosapi.mapper.ShipmentStatusMapper;
import com.misenvios.misenviosapi.model.Shipment;
import com.misenvios.misenviosapi.model.ShipmentStatus;
import com.misenvios.misenviosapi.model.User;
import com.misenvios.misenviosapi.repository.ShipmentRepository;
import com.misenvios.misenviosapi.repository.ShipmentStatusRepository;
import com.misenvios.misenviosapi.repository.UserRepository;
import com.misenvios.misenviosapi.service.IShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipmentService implements IShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShipmentStatusService shipmentStatusService;
    @Autowired
    private ShipmentStatusRepository shipmentStatusRepository;
    @Autowired
    private CourierResolverService courierResolverService;


    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public ShipmentSummaryDTO newShipment(Long userId, ShipmentRequestDTO dto) {
        if (shipmentRepository.findByTrackingCode(dto.getTrackingCode()).isPresent()) {
            throw new ShipmentAlreadyExistsException();
        }
        User user = getById(userId);
        Shipment shipment = ShipmentMapper.toEntity(dto, user);
        shipment.setCourier(courierResolverService.resolve(shipment.getTrackingCode()));
        Shipment shipmentSaved = shipmentRepository.save(shipment);
        return ShipmentMapper.toSummaryDTO(shipmentSaved);
    }

    public ShipmentSummaryDTO getShipment(Long shipmentId) throws Exception {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));
        shipment = shipmentStatusService.updateShipmentStatus(shipment);
        return ShipmentMapper.toSummaryDTO(shipment);
    }

    @Override
    public List<ShipmentSummaryDTO> getShipments(Long id) throws Exception {
        List<Shipment> shipments = shipmentRepository.findByUserIdAndActiveTrue(id);
        List<ShipmentSummaryDTO> shipmentsDto = new ArrayList<>();

        for (Shipment shipment : shipments) {
            if (!"Entregado".equals(shipment.getLastStatus())){
                shipment = shipmentStatusService.updateShipmentStatus(shipment);
            }
            ShipmentSummaryDTO dto = ShipmentMapper.toSummaryDTO(shipment);
            shipmentsDto.add(dto);
        }

        return shipmentsDto;
    }

    @Override
    public ShipmentDetailDTO getShipmentDetail(Long id) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));

        List<ShipmentStatusDTO> listShipmentStatusDTO = new ArrayList<>();
        List<ShipmentStatus> ListShipmentStatus = shipmentStatusRepository.findByShipmentId(id);

        for (ShipmentStatus shipmentStatus : ListShipmentStatus){
            ShipmentStatusDTO shipmentStatusDTO = ShipmentStatusMapper.toDTO(shipmentStatus);
            listShipmentStatusDTO.add(shipmentStatusDTO);
        }

        return ShipmentMapper.toDetailDTO(shipment, listShipmentStatusDTO);
    }

    @Override
    public void deleteShipment(Long id) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));
        shipmentStatusRepository.deleteByShipmentId(id);
        shipment.setActive(false);
        shipmentRepository.save(shipment);

    }
}
