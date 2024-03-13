package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.dto.OrderDto;
import com.bogdan.courierapp.entity.Order;
import com.bogdan.courierapp.service.inter.OrderService;
import com.bogdan.courierapp.validation.UUIDChecker;
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
@Tag(description = "manager controller for app managers", name = "supportManager-controller")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public Order getOrderById(@UUIDChecker @PathVariable("id") String id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/byRest")
    public List<Order> getOrdersByRestaurant(@UUIDChecker @RequestParam String restaurantId) {
        return orderService.getAllOrdersByRestaurant(restaurantId);
    }

    @PostMapping
    public Order createOrder(@RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable("id") @UUIDChecker String orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.ok("Order with ID " + orderId + " has been deleted");
    }


}
