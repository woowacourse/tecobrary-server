package com.woowacourse.tecobrary.service.dto;

import com.woowacourse.tecobrary.domain.Book;
import lombok.Getter;

@Getter
public class BookInfoResponseDto {

    private String url;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String desc;

    private BookInfoResponseDto(Book book) {
        this.url = book.getUrl();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.isbn = book.getIsbn();
        this.desc = book.getDesc();
    }

    public static BookInfoResponseDto create(Book book) {
        return new BookInfoResponseDto(book);
    }
}
