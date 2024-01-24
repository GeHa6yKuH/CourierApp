package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.dto.RestaurantDto;
import com.bogdan.courierapp.entity.Restaurant;
import com.bogdan.courierapp.service.inter.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/rest")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PutMapping(name = "/create")
    public Restaurant createRest(@RequestBody RestaurantDto restaurantDto) {
        return restaurantService.create(restaurantDto);
    }
}
