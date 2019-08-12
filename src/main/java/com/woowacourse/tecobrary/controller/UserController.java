package com.woowacourse.tecobrary.controller;

import com.google.gson.Gson;
import com.woowacourse.tecobrary.domain.User;
import com.woowacourse.tecobrary.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
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

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody String json) {
        Gson gson = new Gson();
        Map map = gson.fromJson(json, Map.class);

        userService.updateUserName(id, (String) map.get("newName"));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
