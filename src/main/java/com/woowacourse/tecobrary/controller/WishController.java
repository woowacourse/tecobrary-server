package com.woowacourse.tecobrary.controller;

import com.woowacourse.tecobrary.controller.dto.WishRequestDto;
import com.woowacourse.tecobrary.service.WishService;
import com.woowacourse.tecobrary.service.dto.WishResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishes")
public class WishController {
    private WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @PostMapping
    public ResponseEntity<WishResponseDto> createHopeBook(@RequestBody WishRequestDto wishRequestDto) {
        return new ResponseEntity<>(wishService.save(wishRequestDto), HttpStatus.OK);
    }

}
