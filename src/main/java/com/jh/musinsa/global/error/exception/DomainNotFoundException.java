package com.jh.musinsa.global.error.exception;

import org.springframework.http.HttpStatus;

public class DomainNotFoundException extends BusinessException {
    public DomainNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
