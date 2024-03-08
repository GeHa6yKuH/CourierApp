package com.bogdan.courierapp.dto;

import lombok.Value;

@Value
public class ErrorExtension {

    String message;
    String errorCode;
}