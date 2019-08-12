package com.woowacourse.tecobrary.controller;

import com.woowacourse.tecobrary.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
    private UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/github")
    public ResponseEntity registerGithubUser() {
        userService.saveCurrentGithubUser();
        return new ResponseEntity(HttpStatus.OK);
    }
}
