package com.woowacourse.tecobrary.security;

import com.woowacourse.tecobrary.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentAuthentication {
    public static void change(User user) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user.getName(), null, user.getAuthorities()));
    }
}
