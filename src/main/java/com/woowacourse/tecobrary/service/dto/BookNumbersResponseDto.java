package com.woowacourse.tecobrary.service.dto;

import lombok.Getter;

@Getter
public class BookNumbersResponseDto {

    private Integer numberOfBooks;

    public BookNumbersResponseDto(Integer numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }
}
