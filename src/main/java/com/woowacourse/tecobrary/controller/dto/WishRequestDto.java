package com.woowacourse.tecobrary.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class WishRequestDto {
    private String image;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String desc;
    private Long userId;

    @Builder
    public WishRequestDto(String image, String title, String author, String publisher, String isbn, String desc, Long userId) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.desc = desc;
        this.userId = userId;
    }
}
