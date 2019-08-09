package com.woowacourse.tecobrary.controller;

import com.woowacourse.tecobrary.service.dto.BookNumbersResponseDto;
import com.woowacourse.tecobrary.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/counts")
    public BookNumbersResponseDto numberOfBooks() {
        return bookService.numberOfBooks();
    }
}
