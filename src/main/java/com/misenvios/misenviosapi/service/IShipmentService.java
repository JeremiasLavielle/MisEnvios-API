package com.misenvios.misenviosapi.service;

import com.misenvios.misenviosapi.dto.ShipmentDetailDTO;
import com.misenvios.misenviosapi.dto.ShipmentRequestDTO;
import com.misenvios.misenviosapi.dto.ShipmentSummaryDTO;
import java.util.List;

public interface IShipmentService {
    ShipmentSummaryDTO getShipment(Long id) throws Exception;
    List<ShipmentSummaryDTO> getShipments(Long id) throws Exception;
    ShipmentDetailDTO getShipmentDetail(Long id);
    ShipmentSummaryDTO newShipment(Long userId, ShipmentRequestDTO dto);
    void deleteShipment(Long id);
}
