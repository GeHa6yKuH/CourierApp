package com.bogdan.courierapp.service.impl;

import com.bogdan.courierapp.dto.RestaurantDto;
import com.bogdan.courierapp.entity.AppRole;
import com.bogdan.courierapp.entity.Restaurant;
import com.bogdan.courierapp.entity.enums.RestaurantStatus;
import com.bogdan.courierapp.exception.AppRoleNotFoundException;
import com.bogdan.courierapp.exception.ErrorMessage;
import com.bogdan.courierapp.exception.RestaurantNotFoundException;
import com.bogdan.courierapp.exception.RestaurantWithThisNameAlreadyExistsException;
import com.bogdan.courierapp.repository.AppRoleRepository;
import com.bogdan.courierapp.repository.RestaurantRepository;
import com.bogdan.courierapp.service.inter.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final AppRoleRepository appRoleRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Restaurant create(RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantRepository.getRestaurantByRestaurantName(restaurantDto.getRestaurantName());
        AppRole appRole = appRoleRepository.findById(UUID.fromString("60c9bbdd-f631-414f-a12e-63ed1119b264"))
                .orElseThrow(() -> new AppRoleNotFoundException(ErrorMessage.APP_ROLE_NOT_FOUND));
        if (restaurant != null) throw new RestaurantWithThisNameAlreadyExistsException();
        Restaurant restaurant1 = Restaurant.builder()
                .restaurantName(restaurantDto.getRestaurantName())
                .owner(restaurantDto.getOwner())
                .creationDate(new Date(System.currentTimeMillis()))
                .appRole(appRole)
                .status(RestaurantStatus.ACTIVE)
                .build();
        restaurantRepository.save(restaurant1);
//        System.out.println("*******************");
        return ResponseEntity.ok().body(restaurant1).getBody();
    }

    @Override
    public Restaurant getRestaurantById(String id) {
        return restaurantRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RestaurantNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND));
    }

    @Override
    public List<Restaurant> getRestaurantsByDeliveryZoneId(String deliveryZoneId) {
        return restaurantRepository.getRestaurantsByDeliveryZoneId(UUID.fromString(deliveryZoneId));
    }

    @Override
    public List<Restaurant> getRestaurantsByOwner(String owner) {
        return restaurantRepository.getRestaurantsByOwner(owner);
    }

    @Override
    public void deleteById(String restaurantId) {
        getRestaurantById(restaurantId);
        restaurantRepository.deleteById(UUID.fromString(restaurantId));
    }
}
