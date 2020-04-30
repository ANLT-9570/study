package com.micro.item.pojo;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class asd {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(Myconfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for(String s:beanDefinitionNames){
            System.out.println(s);
        }
    }
}
