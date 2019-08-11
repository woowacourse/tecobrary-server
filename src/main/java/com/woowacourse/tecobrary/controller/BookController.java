package com.woowacourse.tecobrary.controller;

import com.woowacourse.tecobrary.service.BookService;
import com.woowacourse.tecobrary.service.dto.BookCreateRequestDto;
import com.woowacourse.tecobrary.service.dto.BookCreateResponseDto;
import com.woowacourse.tecobrary.service.dto.BookInfoResponseDto;
import com.woowacourse.tecobrary.service.dto.BookNumbersResponseDto;
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
    public BookInfoResponseDto findBook(@PathVariable Long id){
        return bookService.findBookById(id);
    }

    @PostMapping
    public BookCreateResponseDto createBook(@RequestBody BookCreateRequestDto bookCreateRequestDto){
        return bookService.createBook(bookCreateRequestDto);
    }
}
