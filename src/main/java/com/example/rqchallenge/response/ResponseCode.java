package com.example.rqchallenge.response;

import org.springframework.http.HttpStatus;

public enum ResponseCode {

    SUCCESS(HttpStatus.OK, "Your request has been successfully completed."),
    CREATED(HttpStatus.CREATED  , "Your request has been successfully completed."),
    DELETED(HttpStatus.OK,"successfully! deleted Record."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND,"Resource not found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
    private final String message;

    private final HttpStatus httpStatusCode;
    ResponseCode(HttpStatus httpStatusCode, String message) {

        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    // Method to get the message associated with the enum constant
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }

}
