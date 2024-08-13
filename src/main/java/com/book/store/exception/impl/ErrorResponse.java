package com.book.store.exception.impl;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {

    private final HttpStatus errorCode;
    private final String errorMessage;
    private final LocalDateTime time;

    public ErrorResponse(HttpStatus errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.time = LocalDateTime.now();
    }

}