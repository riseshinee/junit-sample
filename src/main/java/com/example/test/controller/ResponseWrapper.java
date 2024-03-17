package com.example.test.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.example.test.common.exception.ErrorMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseWrapper<T> extends ErrorMessage {

    private T data;
    private int status;
    private String code;

    public ResponseWrapper() {
        this.status = HttpStatus.OK.value();
    }

    public ResponseWrapper(T data) {
        this.status = HttpStatus.OK.value();
        this.data = data;
    }
}
