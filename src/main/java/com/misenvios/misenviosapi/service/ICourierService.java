package com.misenvios.misenviosapi.service;

import com.misenvios.misenviosapi.dto.ShipmentStatusDTO;

import java.util.List;

public interface ICourierService {
    List<ShipmentStatusDTO> getShipmentStatus(String trackingCode) throws Exception;
}
