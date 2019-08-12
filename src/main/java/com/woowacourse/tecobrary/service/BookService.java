package com.woowacourse.tecobrary.service;

import com.woowacourse.tecobrary.domain.Book;
import com.woowacourse.tecobrary.exception.BookNotFoundException;
import com.woowacourse.tecobrary.repository.BookRepository;
import com.woowacourse.tecobrary.service.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
public class BookService {

    private static final Integer PAGE_SIZE = 12;
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookNumbersResponseDto numberOfBooks() {
        Integer numberOfBooks = bookRepository.findAll().size();
        return new BookNumbersResponseDto(numberOfBooks);
    }

    public BookInfoResponseDto findBookById(Long id) {
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

    public List<BookInfoResponseDto> findBooksByKeyword(String keyword) {
        List<Book> foundBooks = bookRepository.findBooksByTitleContains(keyword);
        return foundBooks.stream()
                .map(BookInfoResponseDto::create)
                .collect(toList());
    }

    public List<BookInfoResponseDto> findBooksByPage(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);
        Page<Book> foundBooksByPage = bookRepository.findAll(pageRequest);
        return foundBooksByPage.getContent().stream()
                .map(BookInfoResponseDto::create)
                .collect(toList());
    }
}
