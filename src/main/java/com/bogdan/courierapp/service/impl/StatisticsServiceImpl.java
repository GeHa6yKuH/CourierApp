package com.bogdan.courierapp.service.impl;

import com.bogdan.courierapp.dto.StatisticsCreateDto;
import com.bogdan.courierapp.dto.StatisticsDto;
import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.entity.Statistics;
import com.bogdan.courierapp.exception.CourierNotFoundException;
import com.bogdan.courierapp.exception.ErrorMessage;
import com.bogdan.courierapp.exception.StatisticsNotFoundException;
import com.bogdan.courierapp.repository.CourierRepository;
import com.bogdan.courierapp.repository.StatisticsRepository;
import com.bogdan.courierapp.service.inter.StatistcsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatistcsService {

    private final StatisticsRepository statisticsRepository;

    private final CourierRepository courierRepository;

    @Override
    public Statistics getStatisticsById(String id) {
        return statisticsRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new StatisticsNotFoundException(ErrorMessage.STATISTICS_NOT_FOUND));
    }

    @Override
    public StatisticsDto createStatisticsByDto(StatisticsCreateDto statisticsCreateDto) {
        Courier courier = courierRepository.findById(statisticsCreateDto.getCourierId())
                .orElseThrow(() -> new CourierNotFoundException(ErrorMessage.COURIER_NOT_FOUND));
        Statistics statistics = Statistics.builder()
                .from(statisticsCreateDto.getFrom())
                .till(statisticsCreateDto.getTill())
                .courier(courier)
                .earnedMoney(statisticsCreateDto.getEarnedMoney())
                .build();
        statisticsRepository.save(statistics);
        return StatisticsDto.builder()
                .courier(courier)
                .statistics(statistics)
                .build();
    }

    @Override
    public void deleteStatisticsById(String id) {
        getStatisticsById(id);
        courierRepository.deleteById(UUID.fromString(id));
    }


}
