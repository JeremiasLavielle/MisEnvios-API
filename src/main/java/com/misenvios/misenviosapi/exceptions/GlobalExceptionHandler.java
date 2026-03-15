package com.misenvios.misenviosapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ShipmentAlreadyExistsException.class)
    public ResponseEntity<Void> handleShipmentAlreadyExists() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}