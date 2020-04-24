package com.micro.common.utils;

import com.micro.common.exception.BaseException;

public class FailureResult extends Result {

    public FailureResult(String code, String message, Object data){
        super(code,message,null);
    }

    public FailureResult(BaseException e) {
        super(e.getCode(),e.getMessage(),null);
    }

    public FailureResult(Tuple2<String, String> info, Object o) {
        super(info,o);
    }
}
