package com.woowacourse.tecobrary.service;

import com.woowacourse.tecobrary.domain.Role;
import com.woowacourse.tecobrary.domain.RoleRepository;
import com.woowacourse.tecobrary.exception.NotFoundRoleException;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByType(Role.Type type) {
        return roleRepository.findByName(type.name())
                .orElseThrow(NotFoundRoleException::new);
    }

    public Role findById(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(NotFoundRoleException::new);
    }
}
