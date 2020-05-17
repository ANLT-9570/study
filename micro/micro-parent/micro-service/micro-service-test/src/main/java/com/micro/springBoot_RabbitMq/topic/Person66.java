package com.micro.springBoot_RabbitMq.topic;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@NoArgsConstructor
@ToString
public class Person66 implements Serializable {

    String name;
    String age;
    String sex;

    public Person66(String name, String age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
