package com.maanz.tutor.util;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {

    private static final long serialVersionUID = 2881116644451554263L;

    private final HttpStatus httpStatusCode;

    public CustomException(HttpStatus httpStatusCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }
}