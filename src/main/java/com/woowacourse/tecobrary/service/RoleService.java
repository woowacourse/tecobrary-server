package com.woowacourse.tecobrary.service;

import com.woowacourse.tecobrary.domain.Role;
import com.woowacourse.tecobrary.domain.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByType(Role.Type type) {
        return roleRepository.findByName(type.name())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 역할입니다."));
    }
}
