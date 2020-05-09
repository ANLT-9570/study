package com.micro.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class Test1 implements Serializable {
    String code;
    String name;

    public Test1(String code, String name) {
        this.code = code;
        this.name = name;
    }
}

