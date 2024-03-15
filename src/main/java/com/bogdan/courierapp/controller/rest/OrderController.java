package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.dto.ErrorDto;
import com.bogdan.courierapp.dto.OrderDto;
import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.entity.Order;
import com.bogdan.courierapp.service.inter.OrderService;
import com.bogdan.courierapp.validation.UUIDChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/order")
@Tag(description = "controller for managing orders", name = "OrderController")
public class OrderController {

    private final OrderService orderService;

    // *****************************************getOrderById()*****************************************

    @Operation(summary = "Retrieve order by ID",
            description = "Returns the order with the provided ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order found",
                            content = {@Content(schema = @Schema(implementation = Order.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "No order found with the provided ID",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "Internal server error occurred",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")})
            })
    @GetMapping("/{id}")
    public Order getOrderById(@UUIDChecker @PathVariable("id") String id) {
        return orderService.getOrderById(id);
    }

    // *****************************************getOrdersByRestaurant()*****************************************

    @Operation(summary = "Retrieve orders by restaurant ID",
            description = "Returns a list of orders associated with the provided restaurant ID",
            parameters = {
                    @Parameter(
                            name = "restaurantId",
                            description = "ID of the restaurant wired with requested orders",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "string", format = "uuid")
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Orders found",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = Order.class)),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "No orders found for the provided restaurant ID",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "Internal server error occurred",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")})
            })
    @GetMapping("/byRest")
    public List<Order> getOrdersByRestaurant(@UUIDChecker @RequestParam String restaurantId) {
        return orderService.getAllOrdersByRestaurant(restaurantId);
    }

    // *****************************************createOrder()*****************************************

    @Operation(summary = "Create a new order",
            description = "Creates a new order based on the provided OrderDto object",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Insert JSON format data according to OrderDto class",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "New order successfully created",
                            content = {@Content(schema = @Schema(implementation = Order.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "Non existing restaurantId or status provided",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "Internal server error occurred",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "403", description = "permission denied",
                            content = {@Content(schema = @Schema(implementation = Courier.class),
                                    mediaType = "application/json")})
            }
    )
    @PostMapping
    public Order createOrder(@RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    // *****************************************deleteOrderById()*****************************************

    @Operation(summary = "Delete order by ID",
            description = "Deletes the order with the provided ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order with provided ID successfully deleted",
                            content = {@Content(schema = @Schema(defaultValue = "Order successfully deleted"),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "No order found with the provided ID",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
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
    public ResponseEntity<String> deleteOrderById(@PathVariable("id") @UUIDChecker String orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.ok("Order with ID " + orderId + " has been deleted");
    }


}
