package com.bogdan.courierapp.mapper;

import com.bogdan.courierapp.dto.CourierDto;
import com.bogdan.courierapp.entity.Courier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourierMapper {
    @Mapping(source = "deliveryZone.id", target = "deliveryZoneId")
    CourierDto toDto(Courier courier);
}
