package com.woowacourse.tecobrary.controller;

import com.woowacourse.tecobrary.service.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
    private RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/github")
    public ResponseEntity registerGithubUser() {
        registerService.registerGithubUser();
        return new ResponseEntity(HttpStatus.OK);
    }
}
