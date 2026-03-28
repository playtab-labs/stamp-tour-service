package com.playtab.stamptourservice.common.exception;

// 이미 방문한 스팟일 때 사용하는 예외
public class AlreadyVisitedException extends RuntimeException {

    public AlreadyVisitedException(String message) {
        super(message);
    }
}