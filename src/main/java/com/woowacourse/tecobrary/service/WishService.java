package com.woowacourse.tecobrary.service;

import com.woowacourse.tecobrary.controller.dto.WishRequestDto;
import com.woowacourse.tecobrary.domain.model.Wish;
import com.woowacourse.tecobrary.exception.WishNotFoundException;
import com.woowacourse.tecobrary.repository.WishRepository;
import com.woowacourse.tecobrary.service.dto.WishResponseDto;
import org.springframework.stereotype.Service;

@Service
public class WishService {
    private WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public WishResponseDto save(WishRequestDto wishRequestDto) {
        Wish saveWish = Wish.builder()
                .image(wishRequestDto.getImage()).title(wishRequestDto.getTitle())
                .author(wishRequestDto.getAuthor()).publisher(wishRequestDto.getPublisher())
                .isbn(wishRequestDto.getIsbn()).desc(wishRequestDto.getDesc())
                .userId(wishRequestDto.getUserId()).build();

        wishRepository.save(saveWish);

        return WishResponseDto.builder()
                .title(saveWish.getTitle()).build();
    }

    public WishResponseDto findByTitle(String title) {
        Wish foundWish = wishRepository.findByTitle(title)
                .orElseThrow(() -> new WishNotFoundException("요청한 책을 찾을 수 없습니다."));

        return WishResponseDto.builder()
                .id(foundWish.getId()).title(foundWish.getTitle())
                .image(foundWish.getImage()).author(foundWish.getAuthor())
                .publisher(foundWish.getPublisher()).isbn(foundWish.getIsbn())
                .desc(foundWish.getDesc()).userId(foundWish.getUserId())
                .build();
    }
}
