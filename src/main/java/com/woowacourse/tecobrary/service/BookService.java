package com.woowacourse.tecobrary.service;

import com.woowacourse.tecobrary.domain.Book;
import com.woowacourse.tecobrary.exception.BookNotFoundException;
import com.woowacourse.tecobrary.repository.BookRepository;
import com.woowacourse.tecobrary.service.dto.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


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
        // Todo : 예외처리하기
        Book book = bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);

        return BookInfoResponseDto.create(book);
    }

    public BookCreateResponseDto createBook(BookCreateRequestDto bookCreateRequestDto) {
        Book book = bookRepository.save(bookCreateRequestDto.toEntity());
        return BookCreateResponseDto.create(book);
    }

    @Transactional
    public BookDeleteResponseDto deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);

        bookRepository.deleteById(id);
        return BookDeleteResponseDto.create(book);
    }
}
