package com.woowacourse.tecobrary.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookInfoResponseDto {

    private String url;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String desc;
}
