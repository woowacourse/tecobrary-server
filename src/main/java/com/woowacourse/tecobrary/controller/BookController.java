package com.woowacourse.tecobrary.controller;

import com.woowacourse.tecobrary.service.BookService;
import com.woowacourse.tecobrary.service.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping
    public BookInfoResponseDto findBookById(@RequestParam Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping
    public BookCreateResponseDto createBook(@RequestBody BookCreateRequestDto bookCreateRequestDto) {
        return bookService.createBook(bookCreateRequestDto);
    }

    @DeleteMapping
    public BookDeleteResponseDto deleteBook(@RequestParam Long id) {
        return bookService.deleteBook(id);
    }

    @GetMapping("/{keyword}")
    public List<BookInfoResponseDto> findBookByKeyword(@PathVariable String keyword) {
        return bookService.findBooksByKeyword(keyword);
    }
}
