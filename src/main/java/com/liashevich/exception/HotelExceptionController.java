package com.liashevich.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HotelExceptionController {

    @ExceptionHandler(value = HotelNotFoundException.class)
    public ResponseEntity<Object> exception(RuntimeException runtimeException) {
        return new ResponseEntity<>("hotel is not found", HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = RequiredFieldMissingException.class)
    public ResponseEntity<Object> missingRequiredField(RequiredFieldMissingException runtimeException) {
        return new ResponseEntity<>("missing: " + runtimeException.getMissingFields() , HttpStatus.BAD_REQUEST);
    }

}
