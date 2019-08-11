package com.woowacourse.tecobrary.controller;

import com.woowacourse.tecobrary.service.BookService;
import com.woowacourse.tecobrary.service.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/{id}")
    public BookInfoResponseDto findBook(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping
    public BookCreateResponseDto createBook(@RequestBody BookCreateRequestDto bookCreateRequestDto) {
        return bookService.createBook(bookCreateRequestDto);
    }

    @DeleteMapping("/{id}")
    public BookDeleteResponseDto deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }
}
