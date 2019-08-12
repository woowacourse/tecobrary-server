package com.woowacourse.tecobrary.service;

import com.woowacourse.tecobrary.domain.Role;
import com.woowacourse.tecobrary.domain.User;
import com.woowacourse.tecobrary.security.CurrentAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RegisterService {
    private UserService userService;
    private RoleService roleService;

    public RegisterService(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public User registerGithubUser() {
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> map = (HashMap<String, String>) authentication.getUserAuthentication().getDetails();
        Object githubId = map.get("id");
        int id = Integer.parseInt(githubId.toString());

        Optional<User> user = userService.findByGithubId(id);
        if (user.isPresent()) {
            CurrentAuthentication.change(user.get());
            return user.get();
        }

        Role role = roleService.findByType(Role.Type.ROLE_NONE);
        User savedUser = userService.save(User.builder()
                .githubId(id)
                .email(map.getOrDefault("email", null))
                .name(map.getOrDefault("name", null))
                .avatar(map.getOrDefault("avatar_url", null))
                .roles(Arrays.asList(role))
                .build());
        CurrentAuthentication.change(savedUser);
        return savedUser;
    }
}
