package com.example.securitydemo.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class BadRequestExecption extends HttpClientErrorException {
    /**
     * Constructor with a status code only.
     *
     * @param statusCode
     */
    public BadRequestExecption(HttpStatus statusCode) {
        super(statusCode);
    }

    /**
     * Constructor with a status code and status text.
     *
     * @param statusCode
     * @param statusText
     */
    public BadRequestExecption(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
