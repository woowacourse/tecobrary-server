package com.woowacourse.tecobrary.service;

import com.woowacourse.tecobrary.domain.Book;
import com.woowacourse.tecobrary.exception.BookNotFoundException;
import com.woowacourse.tecobrary.repository.BookRepository;
import com.woowacourse.tecobrary.service.dto.BookInfoResponseDto;
import com.woowacourse.tecobrary.service.dto.BookNumbersResponseDto;
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

    public BookInfoResponseDto findBookById(Long id) {
        // Todo : 예외처리하기, Dto 생성 어떻게 할 것인가 논의
        Book book = bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);

        return BookInfoResponseDto.builder()
                .url(book.getUrl())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .isbn(book.getIsbn())
                .desc(book.getDesc())
                .build();
    }
}
