package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.dto.RestaurantDto;
import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.entity.Restaurant;
import com.bogdan.courierapp.service.inter.RestaurantService;
import com.bogdan.courierapp.validation.UUIDChecker;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/create")
    public Restaurant createRest(@RequestBody RestaurantDto restaurantDto) {
        return restaurantService.create(restaurantDto);
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@UUIDChecker @PathVariable("id") String id){
        return restaurantService.getRestaurantById(id);
    }
}
