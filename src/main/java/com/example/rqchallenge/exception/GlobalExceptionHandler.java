package com.example.rqchallenge.exception;

import static com.example.rqchallenge.constant.Constants.FAILED;

import com.example.rqchallenge.Interceptors.RequestLoggingInterceptor;
import com.example.rqchallenge.response.CustomResponse;
import com.example.rqchallenge.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    private RequestLoggingInterceptor requestLoggingInterceptor;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse> handleException(Exception ex) {
        log.error("Exception while processing request body {} with error code {} ",
            requestLoggingInterceptor.getRequestBody(),
            ex.getMessage());
        return ResponseEntity.status(ResponseCode.INTERNAL_SERVER_ERROR.getHttpStatusCode())
            .body(CustomResponse.builder()
                .code(ResponseCode.INTERNAL_SERVER_ERROR)
                .status(FAILED)
                .message(ResponseCode.INTERNAL_SERVER_ERROR.getMessage())
                .build());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomResponse> handleCustomException(CustomException ex) {
        log.error("CustomException while processing request body {} with error code {} ",
            requestLoggingInterceptor.getRequestBody(),
            ex.getMessage());
        return ResponseEntity.status(ex.getErrorCode().getHttpStatusCode())
            .body(CustomResponse.builder()
                .code(ex.getErrorCode())
                .status(FAILED)
                .message(ex.getErrorCode().getMessage())
                .build());
    }
}
