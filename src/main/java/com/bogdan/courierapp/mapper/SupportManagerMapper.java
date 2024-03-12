package com.bogdan.courierapp.mapper;

import com.bogdan.courierapp.dto.SupportManagerDto;
import com.bogdan.courierapp.entity.SupportManager;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupportManagerMapper {
    SupportManager toEntity(SupportManagerDto supportManagerDto);

    SupportManagerDto toDto(SupportManager save);

}
