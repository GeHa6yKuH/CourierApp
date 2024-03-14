package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.dto.CourierDto;
import com.bogdan.courierapp.dto.CourierUpdate;
import com.bogdan.courierapp.dto.ErrorDto;
import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.service.inter.CourierService;
import com.bogdan.courierapp.validation.UUIDChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
@RequestMapping("/courier")
@Tag(description = "courier rest requests managing controller", name = "CourierController")
public class CourierController {

    private final CourierService courierService;

    // *****************************************getCourierById()*****************************************


    @GetMapping("/{id}")
    @Operation(summary = "basic courier get rest method by id",
            description = "returns a courier from database for given id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "courier found",
                            content = {@Content(schema = @Schema(implementation = Courier.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "no such courier found",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "internal server error occurred",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")})
            })
    public Courier getCourierById(@UUIDChecker @PathVariable("id") String id) {
        return courierService.getCourierById(id);
    }

    // *****************************************getCourierDtoById()*****************************************

    @GetMapping("/do/{id}")
    @Operation(summary = "courier get method returning a customized dto",
            description = "returns only essential courier fields described in CourierDto class" +
                    ",casting courier entity to CourierDto",
            responses = {
                    @ApiResponse(responseCode = "200", description = "courier found and casted",
                            content = {@Content(schema = @Schema(implementation = CourierDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "no such courier found",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "internal server error occurred",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")})
            }
    )
    public CourierDto getCourierDtoById(@UUIDChecker @PathVariable("id") String id) {
        return courierService.getCourierDtoById(id);
    }

    // *****************************************createCourier()*****************************************

    @PostMapping
    @Operation(summary = "courier rest method of type post creating courier" +
            " in database from provided CourierDto object",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "insert jason format data according to Courier Entity class",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Courier.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "new courier successfully added",
                            content = {@Content(schema = @Schema(implementation = Courier.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "provided non existing " +
                            "deliveryZoneId in CourierDto object",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")})
            }
    )
    public Courier createCourier(@RequestBody CourierDto courier) {
        return courierService.createCourier(courier);
    }

// *****************************************updateCourierName()*****************************************

    @Operation(summary = "basic courier put rest method modifying courier in database",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the courier",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "string", format = "uuid")
                    ),
                    @Parameter(
                            name = "name",
                            description = "New name for the courier",
                            required = true,
                            in = ParameterIn.QUERY
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Courier with provided id successfully updated" +
                            "to have the new name",
                            content = {@Content(schema = @Schema(defaultValue = "Courier name updated"),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "no such courier found",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "internal server error occurred",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")})
            }
    )
    @PutMapping
    public ResponseEntity<String> updateCourierName(
            @UUIDChecker @RequestParam String id,
            @RequestParam String name
    ) {
        courierService.updateCourierName(id, name);
        return ResponseEntity.ok("Courier with ID " + id + " updated name to  " + name);
    }

// *****************************************updateCourierManager()*****************************************

    @Operation(summary = "courier updating method modifying changing manager for courier",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the courier",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "string", format = "uuid")
                    ),
                    @Parameter(
                            name = "managerId",
                            description = "New manager for the courier",
                            required = true,
                            in = ParameterIn.QUERY
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Courier with provided id successfully updated" +
                            "to have the new name",
                            content = {@Content(schema = @Schema(defaultValue = "Courier manager updated"),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "no such courier found",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "internal server error occurred",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")})
            }
    )
    @PutMapping("/updating")
    public ResponseEntity<String> updateCourierManager(
            @RequestParam @UUIDChecker String id,
            @RequestParam @UUIDChecker String managerId
    ) {
        courierService.updateCourierManager(id, managerId);
        return ResponseEntity.ok("Courier with ID " + id + " has new manager with id " + managerId);
    }

// *****************************************updateCourierMoreInfo()*****************************************

    @Operation(summary = "complex courier information updating method",
            description = "Updating many courier fields at once using CourierUpdate class",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "insert jason format data according to CourierUpdate class",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CourierUpdate.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Courier with provided id successfully updated" +
                            "to have new manager",
                            content = {@Content(schema = @Schema(defaultValue = "Courier successfully updated"),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "no such courier found",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "internal server error occurred",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")})
            })
    @PutMapping("/complex")
    public ResponseEntity<Courier> updateCourierMoreInfo(@RequestBody CourierUpdate courierUpdate) {
        Courier courier = courierService.updateComplexCourier(courierUpdate);
        return ResponseEntity.ok(courier);
    }

// *****************************************deleteById()*****************************************

    @Operation(summary = "courier delete method by id",
            description = "deletes a courier with given id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Courier with provided id successfully deleted",
                            content = {@Content(schema = @Schema(defaultValue = "Courier successfully deleted"),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "no such courier found",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "internal server error occurred",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")})
            })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") @UUIDChecker String courierId) {
        courierService.deleteById(courierId);
        return ResponseEntity.ok("Courier with ID " + courierId + " has been deleted");
    }

}
