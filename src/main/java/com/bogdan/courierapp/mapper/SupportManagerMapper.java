package com.bogdan.courierapp.mapper;

import com.bogdan.courierapp.dto.SupportManagerDto;
import com.bogdan.courierapp.entity.SupportManager;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupportManagerMapper {

    SupportManagerDto toDto(SupportManager save);

}
