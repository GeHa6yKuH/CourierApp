package com.bogdan.courierapp.service.impl;

import com.bogdan.courierapp.dto.RestaurantDto;
import com.bogdan.courierapp.entity.Restaurant;
import com.bogdan.courierapp.exception.RestaurantWithThisNameAlreadyExistsException;
import com.bogdan.courierapp.mapper.RestaurantMapper;
import com.bogdan.courierapp.repository.RestaurantRepository;
import com.bogdan.courierapp.service.inter.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final RestaurantMapper restaurantMapper;
    @Override
    public Restaurant create(RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantRepository.getRestaurantByRestaurantName(restaurantDto.getRestaurantName());
        if (restaurant != null) throw new RestaurantWithThisNameAlreadyExistsException();
        else restaurantRepository.save(restaurantMapper.toEntity(restaurantDto));
//        System.out.println("*******************");
        return restaurantRepository.getRestaurantByRestaurantName(restaurantDto.getRestaurantName());
    }
}
