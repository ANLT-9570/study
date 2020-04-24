package com.micro.common.exception;

import com.micro.common.utils.Tuple2;

import java.io.Serializable;

public class ExceptionCode implements Serializable {

    public static class Success{
        final static public Tuple2<String,String> SUCCESS = new Tuple2<>("10000","success");
    }
    public static class Failure{
        final static public Tuple2<String,String> NOT_NULL = new Tuple2<>("40444","failure");
    }
}
