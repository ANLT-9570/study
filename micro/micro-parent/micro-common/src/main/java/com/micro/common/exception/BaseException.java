package com.micro.common.exception;

import lombok.Data;

@Data
public class BaseException extends Exception {
    protected String code;
    protected String message;

    public BaseException(){

    }
}
