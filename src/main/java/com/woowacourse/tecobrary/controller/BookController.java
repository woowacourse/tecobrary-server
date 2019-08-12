package com.woowacourse.tecobrary.controller;

import com.woowacourse.tecobrary.service.BookService;
import com.woowacourse.tecobrary.service.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BookNumbersResponseDto> numberOfBooks() {
        return new ResponseEntity<>(bookService.numberOfBooks(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BookInfoResponseDto> findBookById(@RequestParam Long id) {
        return new ResponseEntity<>(bookService.findBookById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookCreateResponseDto> createBook(@RequestBody BookCreateRequestDto bookCreateRequestDto) {
        return new ResponseEntity<>(bookService.createBook(bookCreateRequestDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<BookDeleteResponseDto> deleteBook(@RequestParam Long id) {
        return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.OK);
    }

    @GetMapping("/{keyword}")
    public ResponseEntity<List<BookInfoResponseDto>> findBookByKeyword(@PathVariable String keyword) {
        return new ResponseEntity<>(bookService.findBooksByKeyword(keyword), HttpStatus.OK);
    }
}
