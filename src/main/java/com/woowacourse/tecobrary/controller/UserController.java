package com.woowacourse.tecobrary.controller;

import com.woowacourse.tecobrary.controller.dto.NewUserNameRequestDto;
import com.woowacourse.tecobrary.service.UserService;
import com.woowacourse.tecobrary.service.dto.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> readUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> readUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUserName(@PathVariable Long id, @RequestBody NewUserNameRequestDto newName) {
        userService.updateUserName(id, newName);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}/roles/{roleId}")
    public ResponseEntity updateUserRole(@PathVariable Long id, @PathVariable("roleId") Long roleId) {
        userService.updateUserRole(id, roleId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
