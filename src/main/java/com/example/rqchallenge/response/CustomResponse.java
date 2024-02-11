package com.example.rqchallenge.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(Include. NON_NULL)
public class CustomResponse<T> {
    private ResponseCode code;
    private String message;
    private String status;
    private T data;
}
