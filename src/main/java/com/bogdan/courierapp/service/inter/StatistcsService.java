package com.bogdan.courierapp.service.inter;

import com.bogdan.courierapp.dto.StatisticsCreateDto;
import com.bogdan.courierapp.dto.StatisticsDto;
import com.bogdan.courierapp.entity.Statistics;

public interface StatistcsService {
    Statistics getStatisticsById(String id);

    StatisticsDto createStatisticsByDto(StatisticsCreateDto statisticsCreateDto);

    void deleteStatisticsById(String id);
}
