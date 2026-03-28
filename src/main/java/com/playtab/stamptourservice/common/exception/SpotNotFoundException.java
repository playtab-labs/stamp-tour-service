package com.playtab.stamptourservice.common.exception;

// 존재하지 않는 스팟일 때 사용하는 예외
public class SpotNotFoundException extends RuntimeException {

    public SpotNotFoundException(String message) {
        super(message);
    }
}