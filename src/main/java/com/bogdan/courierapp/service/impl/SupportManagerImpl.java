package com.bogdan.courierapp.service.impl;

import com.bogdan.courierapp.dto.SupportManagerDto;
import com.bogdan.courierapp.entity.AppRole;
import com.bogdan.courierapp.entity.SupportManager;
import com.bogdan.courierapp.exception.AppRoleNotFoundException;
import com.bogdan.courierapp.exception.ErrorMessage;
import com.bogdan.courierapp.mapper.SupportManagerMapper;
import com.bogdan.courierapp.repository.AppRoleRepository;
import com.bogdan.courierapp.repository.SupportManagerRepository;
import com.bogdan.courierapp.service.inter.SupportManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupportManagerImpl implements SupportManagerService {

    private final SupportManagerRepository supportManagerRepository;

    private final AppRoleRepository appRoleRepository;

    private final SupportManagerMapper supportManagerMapper;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public SupportManager getSupportManagerById(String id) {
        return supportManagerRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("no such support manager"));
    }

    @Override
    public SupportManagerDto createSupportManagerDto(SupportManagerDto supportManagerDto) {
        AppRole appRole = appRoleRepository.findById(UUID.fromString("53da7e2b-7e7f-421e-8b5e-371dd13c2b64"))
                .orElseThrow(() -> new AppRoleNotFoundException(ErrorMessage.APP_ROLE_NOT_FOUND));
        SupportManager supportManager = SupportManager.builder()
                .name(supportManagerDto.getName())
                .password(supportManagerDto.getPassword())
                .appRole(appRole)
                .build();
        return supportManagerMapper.toDto(supportManagerRepository.save(supportManager));
    }

    @Override
    public List<SupportManager> getListOfManagersAfterPattern(String firstOrLastName) {
        return supportManagerRepository.findByPatternName(firstOrLastName);
    }

    @Override
    public List<SupportManager> getListOfManagersAfterName(String name) {
        return supportManagerRepository.findByName(name);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public void deleteById(String managerId) {
        supportManagerRepository.deleteById(UUID.fromString(managerId));
    }
}
