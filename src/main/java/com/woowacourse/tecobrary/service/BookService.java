package com.woowacourse.tecobrary.service;

import com.woowacourse.tecobrary.service.dto.BookNumbersResponseDto;
import com.woowacourse.tecobrary.repository.BookRepository;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookNumbersResponseDto numberOfBooks() {
        Integer numberOfBooks = bookRepository.findAll().size();
        return new BookNumbersResponseDto(numberOfBooks);
    }
}
