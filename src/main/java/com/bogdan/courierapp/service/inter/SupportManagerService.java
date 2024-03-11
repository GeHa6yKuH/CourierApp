package com.bogdan.courierapp.service.inter;

import com.bogdan.courierapp.dto.SupportManagerDto;
import com.bogdan.courierapp.entity.SupportManager;

import java.util.List;

public interface SupportManagerService {

    SupportManager getSupportManagerById(String id);

    SupportManagerDto createSupportManagerDto(SupportManagerDto supportManagerDto);

    List<SupportManager> getListOfManagersAfterPattern(String firstOrLastName);

    List<SupportManager> getListOfManagersAfterName(String name);

    void deleteById(String managerId);
}
