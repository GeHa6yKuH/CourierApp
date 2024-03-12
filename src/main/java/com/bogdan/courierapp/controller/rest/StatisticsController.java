package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.dto.StatisticsCreateDto;
import com.bogdan.courierapp.dto.StatisticsDto;
import com.bogdan.courierapp.entity.Statistics;
import com.bogdan.courierapp.service.inter.StatistcsService;
import com.bogdan.courierapp.validation.UUIDChecker;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/statistics")
@Tag(description = "statistics controller for app managers", name = "Statistics-controller")
public class StatisticsController {

    private final StatistcsService statistcsService;

    @GetMapping("/{id}")
    public Statistics getStatisticsById(@UUIDChecker @PathVariable("id") String id) {
        return statistcsService.getStatisticsById(id);
    }

    @PostMapping
    public StatisticsDto createStatisticsByDto(@RequestBody StatisticsCreateDto statisticsCreateDto) {
        return statistcsService.createStatisticsByDto(statisticsCreateDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStatisticsById(@PathVariable("id") String id) {
        statistcsService.deleteStatisticsById(id);
        return ResponseEntity.ok("statistics with id: " + id + " has been successfully deleted");
    }
}
