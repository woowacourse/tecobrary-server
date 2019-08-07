package com.woowacourse.tecobrary.controller;

import com.woowacourse.tecobrary.domain.User;
import com.woowacourse.tecobrary.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public List<User> readUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User readUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("register/github")
    public void registerGithubUser(HttpServletResponse response) throws IOException {
        userService.saveCurrentGithubUser();
    }
}
