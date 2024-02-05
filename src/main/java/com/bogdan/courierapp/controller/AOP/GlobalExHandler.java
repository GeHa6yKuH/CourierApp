package com.bogdan.courierapp.controller.AOP;

import com.bogdan.courierapp.exception.CourierNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExHandler {
    @ExceptionHandler(CourierNotFoundException.class)
    public ResponseEntity<String> handleCourierNotFoundException(CourierNotFoundException courierNotFoundException) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.I_AM_A_TEAPOT)
                .headers(headers)
                .body("Such courier does not exist in our universe :) " + courierNotFoundException.getMessage());
    }

}
