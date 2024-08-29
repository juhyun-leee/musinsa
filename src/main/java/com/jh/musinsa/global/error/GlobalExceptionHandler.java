package com.jh.musinsa.global.error;

import com.jh.musinsa.global.common.api.ApiError;
import com.jh.musinsa.global.common.api.ApiResult;
import com.jh.musinsa.global.error.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResult<?>> handleException(Exception e) {
        log.error("Unexpected exception occurred: {}", e.getMessage(), e);

        final ApiError error = new ApiError("INTERNAL_SERVER_ERROR", e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResult.error(error));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResult<?>> handleBusinessException(BusinessException e) {
        log.debug("Business exception occurred: {}", e.getMessage(), e);

        final ApiError error = new ApiError(e.getCode(), e.getMessage());

        return ResponseEntity.status(e.getStatus()).body(ApiResult.error(error));
    }
}
