package com.bogdan.courierapp.dto;

import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.entity.Statistics;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StatisticsDto {
    Courier courier;
    Statistics statistics;
}
