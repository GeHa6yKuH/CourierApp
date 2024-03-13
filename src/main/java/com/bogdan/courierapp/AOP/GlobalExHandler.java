package com.bogdan.courierapp.AOP;

import com.bogdan.courierapp.dto.ErrorDto;
import com.bogdan.courierapp.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExHandler {

    @ExceptionHandler(value = AppRoleNotFoundException.class)
    public ResponseEntity<ErrorDto> appRoleNotFoundException(AppRoleNotFoundException exception) {
        ErrorDto errorDto = new ErrorDto(
                "Look at how I wrote id",
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(value = CourierNotFoundException.class)
    public ResponseEntity<ErrorDto> courierNotFoundException(CourierNotFoundException exception) {
        ErrorDto errorDto = new ErrorDto(
                "Look at how I wrote id",
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(value = DeliveryZoneNotFoundException.class)
    public ResponseEntity<ErrorDto> deliveryZoneException(DeliveryZoneNotFoundException exception) {
        ErrorDto errorDto = new ErrorDto(
                "Look at how I wrote id",
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(value = OrderNotFoundException.class)
    public ResponseEntity<ErrorDto> orderNotFoundException(OrderNotFoundException exception) {
        ErrorDto errorDto = new ErrorDto(
                "Look at how I wrote id",
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(value = RestaurantWithThisNameAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> restaurantWithThisNameAlreadyExistsException(RestaurantWithThisNameAlreadyExistsException exception) {
        ErrorDto errorDto = new ErrorDto(
                "Look at how I wrote id",
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(value = StatisticsNotFoundException.class)
    public ResponseEntity<ErrorDto> statisticsNotFoundException(StatisticsNotFoundException exception) {
        ErrorDto errorDto = new ErrorDto(
                "Look at how I wrote id",
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(value = SupportManagerException.class)
    public ResponseEntity<ErrorDto> supportManagerException(SupportManagerException exception) {
        ErrorDto errorDto = new ErrorDto(
                "Look at how I wrote id",
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorDto> exceptionHandler(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ErrorDto(
                                "Something Wrong",
                                exception.getMessage()
                        ));
    }
}
