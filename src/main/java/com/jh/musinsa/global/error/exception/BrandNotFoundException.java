package com.jh.musinsa.global.error.exception;

import org.springframework.http.HttpStatus;

public class BrandNotFoundException extends BusinessException {
    public BrandNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
