package com.bogdan.courierapp.mapper;

import com.bogdan.courierapp.dto.CourierDto;
import com.bogdan.courierapp.entity.Courier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourierMapper {
    CourierDto toDto(Courier courier);
}
