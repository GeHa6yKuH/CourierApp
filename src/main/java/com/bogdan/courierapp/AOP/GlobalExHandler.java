package com.bogdan.courierapp.AOP;

import com.bogdan.courierapp.dto.ErrorResponse;
import com.bogdan.courierapp.exception.CourierNotFoundException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExHandler {
    @ExceptionHandler(CourierNotFoundException.class)
    @ApiResponse(responseCode = "400", description = "Bad request", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))
    })
    public ResponseEntity<String> handleCourierNotFoundException(CourierNotFoundException courierNotFoundException) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.I_AM_A_TEAPOT)
                .headers(headers)
                .body("Such courier does not exist in our universe :) " + courierNotFoundException.getMessage());
    }

}
