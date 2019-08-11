package com.woowacourse.tecobrary.service.dto;

import com.woowacourse.tecobrary.domain.Book;
import lombok.Getter;

@Getter
public class BookDeleteResponseDto {

    private Long id;
    private String url;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String desc;

    private BookDeleteResponseDto(Book book) {
        this.id = book.getId();
        this.url = book.getUrl();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.isbn = book.getIsbn();
        this.desc = book.getDesc();
    }

    public static BookDeleteResponseDto create(Book book) {
        return new BookDeleteResponseDto(book);
    }
}
