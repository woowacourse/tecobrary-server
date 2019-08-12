package com.woowacourse.tecobrary.domain;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserFactory {
    public static User makeGithubMember(Role role) {
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> map = (HashMap<String, String>) authentication.getUserAuthentication().getDetails();
        Object githubId = map.get("id");
        int id = Integer.parseInt(githubId.toString());

        return User.builder()
                .githubId(id)
                .email(map.getOrDefault("email", null))
                .name(map.getOrDefault("name", null))
                .avatar(map.getOrDefault("avatar_url", null))
                .roles(Arrays.asList(role))
                .build();
    }
}
