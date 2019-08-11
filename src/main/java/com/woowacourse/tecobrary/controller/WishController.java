package com.woowacourse.tecobrary.controller;

import com.woowacourse.tecobrary.controller.dto.WishRequestDto;
import com.woowacourse.tecobrary.service.WishService;
import com.woowacourse.tecobrary.service.dto.WishResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishes")
public class WishController {
    private WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @PostMapping
    public ResponseEntity<WishResponseDto> createWishBook(@RequestBody WishRequestDto wishRequestDto) {
        return new ResponseEntity<>(wishService.save(wishRequestDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<WishResponseDto> findByWishBook(@RequestParam("title") String title) {
        return new ResponseEntity<>(wishService.findByTitle(title), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<WishResponseDto> deleteWishBook(@RequestParam("title") String title) {
        return new ResponseEntity<>(wishService.delete(title), HttpStatus.OK);
    }
}
