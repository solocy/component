package com.example.securitydemo.common.execption;

import org.springframework.http.HttpStatus;

public class BadRequestException  extends RuntimeException{

    private HttpStatus httpStatus;

    private String text;

    /**
     * Constructor with a status code and status text.
     *
     * @param statusCode
     * @param statusText
     */
    public BadRequestException(HttpStatus statusCode, String statusText) {
        super(statusText);
        this.httpStatus = statusCode;
        this.text=statusText;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getText() {
        return text;
    }
}
