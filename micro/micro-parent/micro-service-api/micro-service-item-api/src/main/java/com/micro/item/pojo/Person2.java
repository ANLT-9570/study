package com.micro.item.pojo;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component
public class Person2 implements BeanNameAware, ApplicationContextAware {
    public Person2(){
        System.out.println("构造......");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("setBeanName..........");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext........");
    }

    @PostConstruct
    public void init() {
        System.out.println("init.........");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy........");
    }
}
