package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.dto.CourierDto;
import com.bogdan.courierapp.dto.CourierUpdate;
import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.service.inter.CourierService;
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
@RequestMapping("/courier")
@Tag(description = "courier managing controller", name = "courier-controller")
public class CourierController {

    private final CourierService courierService;

    @GetMapping("/{id}")
    @Operation(summary = "basic courier get rest method by id",
            description = "returns a curier from database for given id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "courier found"),
                    @ApiResponse(responseCode = "500", description = "no such courier")
            })
    public Courier getCourierById(@UUIDChecker @PathVariable("id") String id) {
        return courierService.getCourierById(id);
    }

    @GetMapping("/do/{id}")
    @Operation(summary = "rest get method by CourierDto class",
            description = "some dto method",
            responses = {
                    @ApiResponse(responseCode = "200", description = "courier found"),
                    @ApiResponse(responseCode = "404", description = "no such courier")
            }
    )
    public CourierDto getCourierDtoById(@UUIDChecker @PathVariable("id") String id) {
        return courierService.getCourierDtoById(id);
    }

    @PostMapping
    @Operation(summary = "basic courier post rest method creating courier in database",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "insert jason format data according to Courier Entity class",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Courier.class)
                    )
            )
    )
    public Courier createCourier(@RequestBody CourierDto courier) {
        return courierService.createCourier(courier);
    }

    @PutMapping
    @Operation(summary = "basic courier put rest method modifying courier in database",
            parameters = {
                    // todo parameters class check
            }
    )
    public ResponseEntity<String> updateCourierName(
            @UUIDChecker @RequestParam String id,
            @RequestParam String name
    ) {
        courierService.updateCourierName(id, name);
        return ResponseEntity.ok("Courier with ID " + id + " updated name to  " + name);
    }

    @PutMapping("/updating")
    @Operation(summary = "basic courier put rest method modifying courier in database",
            parameters = {
                    // todo parameters class check
            }
    )
    public ResponseEntity<String> updateCourierManager(
            @RequestParam @UUIDChecker String id,
            @RequestParam @UUIDChecker String managerId
    ) {
        courierService.updateCourierManager(id, managerId);
        return ResponseEntity.ok("Courier with ID " + id + " has new manager with id " + managerId);
    }


    @PutMapping("/complex")
    @Operation(summary = "special integrated update courier rest method with CourierUpdate class in database",
            description = "I have no idea",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "insert jason format data according to CourierUpdate class from dto folder",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CourierUpdate.class)
                    )
            ))
    public ResponseEntity<Courier> updateCourierMoreInfo(@RequestBody CourierUpdate courierUpdate) {
        Courier courier = courierService.updateComplexCourier(courierUpdate);
        return ResponseEntity.ok(courier);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "basic courier rest delete method by provided id",
            description = "delets a curier from database by given id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "courier successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "no such courier / can not be deleted")
            })
    public ResponseEntity<String> deleteById(@PathVariable("id") @UUIDChecker String courierId) {
        courierService.deleteById(courierId);
        return ResponseEntity.ok("Courier with ID " + courierId + " has been deleted");
    }

}
