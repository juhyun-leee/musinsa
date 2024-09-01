package com.jh.musinsa.global.error.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
