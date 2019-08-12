package com.woowacourse.tecobrary.exception;


public class BookNotFoundException extends RuntimeException {
    public static final String BOOK_NOT_FOUND_MESSAGE = "해당 책이 존재하지 않습니다";

    public BookNotFoundException() {
        super(BOOK_NOT_FOUND_MESSAGE);
    }
}
