package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.dto.RestaurantDto;
import com.bogdan.courierapp.entity.Restaurant;
import com.bogdan.courierapp.service.inter.RestaurantService;
import com.bogdan.courierapp.validation.UUIDChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
@Validated
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/create")
    public Restaurant createRest(@RequestBody RestaurantDto restaurantDto) {
        return restaurantService.create(restaurantDto);
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@UUIDChecker @PathVariable("id") String id) {
        return restaurantService.getRestaurantById(id);
    }

    @GetMapping("/byZone/{id}")
    public List<Restaurant> getRestaurantsByDeliveryZone(@UUIDChecker @PathVariable("id") String deliveryZoneId) {
        return restaurantService.getRestaurantsByDeliveryZoneId(deliveryZoneId);
    }

    @GetMapping("/byOwner/")
    public List<Restaurant> getRestaurantsByOwner(@RequestParam String owner) {
        return restaurantService.getRestaurantsByOwner(owner);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@UUIDChecker @PathVariable("id") String restaurantId) {
        restaurantService.deleteById(restaurantId);
        return ResponseEntity.ok("restaurant with id: " + restaurantId + "has been deleted");
    }

}
