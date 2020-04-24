package com.micro.common.utils;

import com.micro.common.exception.BaseException;
import com.micro.common.exception.ExceptionCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private String code;
    private String message;
    private Object data;

    public Result(Tuple2<String,String> info,Object o){
        this.code = info._1;
        this.message = info._2;
        this.data = o;
    }
    public Result(String code,String message,Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static FailureResult failureResult(String code,String message){
        return new FailureResult(code,message,null);
    }
    public static FailureResult failureResult(BaseException e){
        return new FailureResult(e);
    }
    public static FailureResult failureResult(Tuple2<String,String> info){
        return new FailureResult(info,"");
    }

    public static SuccessResult successResult(){
        return new SuccessResult(ExceptionCode.Success.SUCCESS,"");
    }
    public static SuccessResult successResult(Object o){
        return new SuccessResult(ExceptionCode.Success.SUCCESS,o);
    }
}
