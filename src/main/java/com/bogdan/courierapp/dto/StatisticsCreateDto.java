package com.bogdan.courierapp.dto;

import lombok.Value;

import java.util.Date;
import java.util.UUID;

@Value
public class StatisticsCreateDto {
    UUID courierId;
    Date from;
    Date till;
    double earnedMoney;
}
