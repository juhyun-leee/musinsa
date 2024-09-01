package com.jh.musinsa.global.error.exception;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends BusinessException {
    public CategoryNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
