package com.example.rqchallenge.response;



import static com.example.rqchallenge.constant.Constants.FAILED;
import static com.example.rqchallenge.constant.Constants.SUCCESS;

import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;


@UtilityClass
public class ResponseBuilderUtil {

    public <T> ResponseEntity buildResponse(ResponseCode responseCode, T data){

      return ResponseEntity.status(responseCode.getHttpStatusCode())
          .body(CustomResponse.<T>builder()
              .status(responseCode.getHttpStatusCode().is2xxSuccessful() ? SUCCESS:FAILED)
              .code(responseCode)
              .message(responseCode.getMessage())
              .data(data)
              .build());
    }

    public ResponseEntity buildResponse(ResponseCode responseCode){
        return buildResponse(responseCode, null);
    }

}
