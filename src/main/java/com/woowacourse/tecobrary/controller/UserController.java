package com.woowacourse.tecobrary.controller;

import com.woowacourse.tecobrary.domain.User;
import com.woowacourse.tecobrary.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> readUser() {
        return userService.findAll();
    }

    @GetMapping("register/github")
    public void registerGithubUser(HttpServletResponse response) throws IOException {
        userService.saveCurrentGithubUser();
    }
}
