package com.playtab.stamptourservice.common.response;

import lombok.Builder;
import lombok.Getter;

// 공통 에러 응답 포맷
@Getter
public class ErrorResponse {

    private final boolean success;
    private final String message;

    @Builder
    public ErrorResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static ErrorResponse failure(String message) {
        return ErrorResponse.builder()
                .success(false)
                .message(message)
                .build();
    }
}