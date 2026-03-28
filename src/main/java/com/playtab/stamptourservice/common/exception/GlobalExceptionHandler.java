package com.playtab.stamptourservice.common.exception;

import com.playtab.stamptourservice.common.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 전역 예외 처리기
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 존재하지 않는 스팟 예외 처리
    @ExceptionHandler(SpotNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSpotNotFoundException(SpotNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.failure(e.getMessage()));
    }

    // 중복 방문 예외 처리
    @ExceptionHandler(AlreadyVisitedException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyVisitedException(AlreadyVisitedException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorResponse.failure(e.getMessage()));
    }

    // 그 외 서버 내부 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.failure("서버 내부 오류가 발생했습니다."));
    }
}