package com.misenvios.misenviosapi.service.impl;


import com.misenvios.misenviosapi.model.Courier;
import org.springframework.stereotype.Service;

@Service
public class CourierResolverService {

    public Courier resolve(String trackingCode){

        if (trackingCode == null || trackingCode.isEmpty()) {
            throw new IllegalArgumentException("Tracking code inválido");
        }

        if (trackingCode.matches("\\d+")){
            return Courier.ANDREANI;
        }
        return Courier.CORREO_ARGENTINO;
    }

}
