package com.jh.musinsa.global.error.exception;

public class ProductNotFoundException extends DomainNotFoundException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
