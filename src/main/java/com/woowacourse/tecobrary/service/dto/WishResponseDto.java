package com.woowacourse.tecobrary.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class WishResponseDto {
    private Long id;
    private String title;
    private String image;
    private String author;
    private String publisher;
    private String isbn;
    private String desc;
    private Long userId;

    @Builder
    public WishResponseDto(Long id, String title, String image, String author, String publisher, String isbn, String desc, Long userId) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.desc = desc;
        this.userId = userId;
    }
}
