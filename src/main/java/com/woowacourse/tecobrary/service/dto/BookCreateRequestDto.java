package com.woowacourse.tecobrary.service.dto;

import com.woowacourse.tecobrary.domain.Book;
import lombok.Getter;

@Getter
public class BookCreateRequestDto {

    private String url;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String desc;

    public Book toEntity() {
        return Book.builder()
                .url(url)
                .title(title)
                .author(author)
                .publisher(publisher)
                .isbn(isbn)
                .desc(desc)
                .build();
    }
}
