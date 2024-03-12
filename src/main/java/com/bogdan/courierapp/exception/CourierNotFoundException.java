package com.bogdan.courierapp.exception;

public class CourierNotFoundException extends RuntimeException {
    public CourierNotFoundException() {
    }

    public CourierNotFoundException(String message) {
        super(message);
    }

    public CourierNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourierNotFoundException(Throwable cause) {
        super(cause);
    }

    public CourierNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
