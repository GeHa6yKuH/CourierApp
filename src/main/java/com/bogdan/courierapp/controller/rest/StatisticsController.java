package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.dto.ErrorDto;
import com.bogdan.courierapp.dto.StatisticsCreateDto;
import com.bogdan.courierapp.dto.StatisticsDto;
import com.bogdan.courierapp.entity.Statistics;
import com.bogdan.courierapp.service.inter.StatistcsService;
import com.bogdan.courierapp.validation.UUIDChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/statistics")
@Tag(description = "controller for managing statistics", name = "StatisticsController")
public class StatisticsController {

    private final StatistcsService statistcsService;

    // *****************************************getStatisticsById()*****************************************

    @Operation(summary = "Retrieve statistics by ID",
            description = "Returns the statistics with the provided ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Statistics found",
                            content = {@Content(schema = @Schema(implementation = Statistics.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "No statistics found with the provided ID",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "Internal server error occurred",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")})
            })
    @GetMapping("/{id}")
    public Statistics getStatisticsById(@UUIDChecker @PathVariable("id") String id) {
        return statistcsService.getStatisticsById(id);
    }

    // *****************************************createStatisticsByDto()*****************************************

    @Operation(summary = "Create statistics",
            description = "Creates statistics and returns StatisticsDto" +
                    " based on the provided StatisticsCreateDto object",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Insert JSON format data according to StatisticsCreateDto class",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StatisticsCreateDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Statistics successfully created",
                            content = {@Content(schema = @Schema(implementation = StatisticsDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "Non existing" +
                            " Courier id provided in StatisticsCreateDto",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")})
            }
    )
    @PostMapping
    public StatisticsDto createStatisticsByDto(@RequestBody StatisticsCreateDto statisticsCreateDto) {
        return statistcsService.createStatisticsByDto(statisticsCreateDto);
    }

    // *****************************************deleteStatisticsById()*****************************************

    @Operation(summary = "Delete statistics by ID",
            description = "Deletes the statistics with the provided ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Statistics with provided ID successfully deleted",
                            content = {@Content(schema = @Schema(defaultValue = "Statistics successfully deleted"),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "No statistics found with the provided ID",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "Internal server error occurred",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")})
            }
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStatisticsById(@PathVariable("id") String id) {
        statistcsService.deleteStatisticsById(id);
        return ResponseEntity.ok("statistics with id: " + id + " has been successfully deleted");
    }
}
