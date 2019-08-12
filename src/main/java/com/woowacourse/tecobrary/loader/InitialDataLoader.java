package com.woowacourse.tecobrary.loader;

import com.woowacourse.tecobrary.domain.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private RoleRepository roleRepository;
    private PrivilegeRepository privilegeRepository;

    private boolean alreadySetup = false;

    public InitialDataLoader(RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        for (Privilege.Type type : Privilege.Type.values()) {
            createPrivilegeIfNotFound(type.name());
        }

        for (Role.Type type : Role.Type.values()) {
            createRoleIfNotFound(type.name(), type.getPrivileges());
        }

        alreadySetup = true;
    }

    @Transactional
    private Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name)
                .orElseGet(() -> privilegeRepository.save(new Privilege(name)));
        return privilege;
    }

    @Transactional
    private Role createRoleIfNotFound(String name, Collection<Privilege.Type> privilegeTypes) {
        return roleRepository.findByName(name).orElseGet(() -> roleRepository.save(
                Role.builder()
                        .name(name)
                        .privileges(findPrivileges(privilegeTypes))
                        .build()));
    }

    private List<Privilege> findPrivileges(Collection<Privilege.Type> privilegeTypes) {
        return privilegeTypes.stream()
                .map(privilege -> privilegeRepository.findByName(privilege.name())
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 권한입니다.")))
                .collect(Collectors.toList());
    }
}
