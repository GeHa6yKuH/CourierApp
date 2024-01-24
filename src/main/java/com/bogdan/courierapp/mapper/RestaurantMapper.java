package com.bogdan.courierapp.mapper;

import com.bogdan.courierapp.dto.RestaurantDto;
import com.bogdan.courierapp.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Date;

@Mapper(componentModel = "spring", imports = {Date.class})
public interface RestaurantMapper {

    @Mapping(defaultValue = "OFFLINE", target = "restaurant.status")
    @Mapping(expression = "java(new Date(System.currentTimeMillis()))", target = "creationDate")
    @Mapping(source = "owner", target = "owner", qualifiedByName = "toUpperCase")
    Restaurant toEntity(RestaurantDto restaurantDto);

    @Named("toUpperCase")
    default String toUpperCase(String owner) {
        return owner.toUpperCase();
    }
}
