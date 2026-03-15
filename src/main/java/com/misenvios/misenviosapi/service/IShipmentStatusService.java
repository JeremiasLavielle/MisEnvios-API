package com.misenvios.misenviosapi.service;

import com.misenvios.misenviosapi.dto.ShipmentStatusDTO;

import java.util.List;

public interface IShipmentStatusService {
    List<ShipmentStatusDTO> getShipmentStatus(Long id);
}
