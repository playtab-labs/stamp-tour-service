package com.playtab.stamptourservice.common.response;

import lombok.Builder;
import lombok.Getter;

// 공통 API 응답 포맷
@Getter
public class ApiResponse<T> {

    // 성공 여부
    private final boolean success;

    // 실제 응답 데이터
    private final T data;

    @Builder
    public ApiResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    // 성공 응답 생성용 정적 메서드
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .build();
    }
}