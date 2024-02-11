package com.example.rqchallenge.exception;


import com.example.rqchallenge.response.ResponseCode;
import java.util.Map;
import lombok.Getter;

public class CustomException extends RuntimeException {

    @Getter
    private final ResponseCode errorCode;
    @Getter
    private final Map<String, Object> context;

    CustomException(ResponseCode errorCode, String message, Map<String, Object> context) {
        super(message);
        this.errorCode = errorCode;
        this.context = context;
    }


    public static CustomException error(ResponseCode errorCode) {
        return new CustomException(errorCode, errorCode.getMessage(), null);
    }

    public static CustomException error(ResponseCode errorCode, String errorMessage, Map context) {
        return new CustomException(errorCode, errorMessage, context);
    }
}
