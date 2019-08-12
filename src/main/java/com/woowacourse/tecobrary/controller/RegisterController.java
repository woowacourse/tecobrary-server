package com.woowacourse.tecobrary.controller;

import com.woowacourse.tecobrary.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/github")
    public void registerGithubUser() {
        userService.saveCurrentGithubUser();
    }
}
