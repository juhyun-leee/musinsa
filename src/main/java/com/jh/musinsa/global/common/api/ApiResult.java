package com.jh.musinsa.global.common.api;

import lombok.Getter;

@Getter
public class ApiResult<T> {
    private final boolean success;
    private final T data;
    private final ApiError error;

    private ApiResult(boolean success, T data, ApiError error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResult<T> ok(T data) {
        return new ApiResult<>(true, data, null);
    }

    public static <T> ApiResult<T> error(ApiError error) {
        return new ApiResult<>(false, null, error);
    }
}
