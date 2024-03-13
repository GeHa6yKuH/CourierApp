package com.bogdan.courierapp.exception;

public class DeliveryZoneNotFoundException extends RuntimeException {
    public DeliveryZoneNotFoundException(String message) {
        super(message);
    }
}
