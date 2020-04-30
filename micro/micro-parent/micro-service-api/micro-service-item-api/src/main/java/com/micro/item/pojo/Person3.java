package com.micro.item.pojo;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Person3 implements BeanFactoryAware, BeanNameAware , InitializingBean , DisposableBean  {
    private String name;
    public Person3(){
        System.out.println("构造1.........");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory.........");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("setBeanName........."+s);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet.........");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy.........");
    }

    public void setName(String name) {
                System.out.println("【注入属性】注入属性name");
               this.name = name;
            }

    @PostConstruct
    public void init(){
        System.out.println("PostConstruct........");
    }
}
