package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.dto.ErrorDto;
import com.bogdan.courierapp.dto.RestaurantDto;
import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.entity.Restaurant;
import com.bogdan.courierapp.service.inter.RestaurantService;
import com.bogdan.courierapp.validation.UUIDChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
@Validated
@Tag(description = "controller for managing restaurants", name = "RestaurantController")
public class RestaurantController {

    private final RestaurantService restaurantService;

    // *****************************************getRestaurantById()*****************************************

    @Operation(summary = "Retrieve restaurant by ID",
            description = "Returns the restaurant with the provided ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Restaurant found",
                            content = {@Content(schema = @Schema(implementation = Restaurant.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "Internal server error occurred",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "403", description = "permission denied",
                            content = {@Content(schema = @Schema(implementation = Courier.class),
                                    mediaType = "application/json")})
            })
    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@UUIDChecker @PathVariable("id") String id) {
        return restaurantService.getRestaurantById(id);
    }

    // *****************************************getRestaurantsByDeliveryZone()*****************************************

    @Operation(summary = "Retrieve restaurants by delivery zone ID",
            description = "Returns a list of restaurants associated with the provided delivery zone ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Restaurants found",
                            content = {@Content(array =
                            @ArraySchema(schema = @Schema(implementation = Restaurant.class)),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "Internal server error occurred",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "403", description = "permission denied",
                            content = {@Content(schema = @Schema(implementation = Courier.class),
                                    mediaType = "application/json")})
            })
    @GetMapping("/byZone/{id}")
    public List<Restaurant> getRestaurantsByDeliveryZone(@UUIDChecker @PathVariable("id") String deliveryZoneId) {
        return restaurantService.getRestaurantsByDeliveryZoneId(deliveryZoneId);
    }

    // *****************************************getRestaurantsByOwner()*****************************************

    @Operation(summary = "Retrieve restaurants by owner",
            description = "Returns a list of restaurants owned by the provided owner",
            parameters = {
                    @Parameter(
                            name = "owner",
                            description = "Owner name for Restaurants search",
                            required = true,
                            in = ParameterIn.QUERY
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Restaurants found",
                            content = {@Content(array =
                            @ArraySchema(schema = @Schema(implementation = Restaurant.class)),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "403", description = "permission denied",
                            content = {@Content(schema = @Schema(implementation = Courier.class),
                                    mediaType = "application/json")})
            })
    @GetMapping("/byOwner/")
    public List<Restaurant> getRestaurantsByOwner(@RequestParam String owner) {
        return restaurantService.getRestaurantsByOwner(owner);
    }

    // *****************************************createRest()*****************************************

    @Operation(summary = "Create a new restaurant",
            description = "Creates a new restaurant based on the provided RestaurantDto object",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Insert JSON format data according to RestaurantDto class",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RestaurantDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "New restaurant successfully created",
                            content = {@Content(schema = @Schema(implementation = Restaurant.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "403", description = "permission denied",
                            content = {@Content(schema = @Schema(implementation = Courier.class),
                                    mediaType = "application/json")})
            }
    )
    @PostMapping("/create")
    public Restaurant createRest(@RequestBody RestaurantDto restaurantDto) {
        return restaurantService.create(restaurantDto);
    }

    // *****************************************deleteById()*****************************************

    @Operation(summary = "Delete restaurant by ID",
            description = "Deletes the restaurant with the provided ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Restaurant with provided ID successfully deleted",
                            content = {@Content(schema = @Schema(defaultValue = "Restaurant successfully deleted"),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "Internal server error occurred",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "403", description = "permission denied",
                            content = {@Content(schema = @Schema(implementation = Courier.class),
                                    mediaType = "application/json")})
            }
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@UUIDChecker @PathVariable("id") String restaurantId) {
        restaurantService.deleteById(restaurantId);
        return ResponseEntity.ok("restaurant with id: " + restaurantId + "has been deleted");
    }

}
