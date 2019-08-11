package com.woowacourse.tecobrary.service.dto;

import com.woowacourse.tecobrary.domain.Book;
import lombok.Getter;

@Getter
public class BookCreateResponseDto {

    private Long id;
    private String url;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String desc;

    private BookCreateResponseDto(Book book) {
        this.id = book.getId();
        this.url = book.getUrl();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.isbn = book.getIsbn();
        this.desc = book.getDesc();
    }

    public static BookCreateResponseDto create(Book book) {
        return new BookCreateResponseDto(book);
    }
}
