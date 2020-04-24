package com.micro.common.utils;

public class SuccessResult extends Result {
    public SuccessResult(Tuple2<String, String> info, Object o) {
        super(info, o);
    }

    public SuccessResult(String code, String message, Object data) {
        super(code, message, data);
    }
}
