package com.woowacourse.tecobrary.exception;

public class NotFoundRoleException extends RuntimeException{
    public static final String NOT_FOUND_ROLE_MESSAGE = "존재하지 않는 역할입니다.";

    public NotFoundRoleException() {
        super(NOT_FOUND_ROLE_MESSAGE);
    }
}
