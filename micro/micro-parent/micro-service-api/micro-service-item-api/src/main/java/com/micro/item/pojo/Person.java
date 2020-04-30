package com.micro.item.pojo;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

//@Data
//@Component
public class Person implements BeanNameAware, BeanPostProcessor, InitializingBean, DisposableBean {
    private String name;
    private String address;
    private int phone;
    private String beanName;
    private BeanFactory beanFactory;
    // 这是BeanNameAware接口方法

    public void setBeanName(String s) {
        System.out.println("【BeanNameAware接口】调用BeanNameAware.setBeanName()");
        this.beanName = s;
    }

    public Person(){
//        System.out.println("init............初始化了...");
    }
//
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        // TODO Auto-generated method stub
//        System.out.println("***************"+beanName+"=>>>>>>>>>>>>>>>>>>>>>"+bean);
//        return bean;
//    }
//
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        // TODO Auto-generated method stub
//        System.out.println("----------------"+beanName+"=<<<<<<<<<<<<<<<<<<<<<<"+bean);
//        return bean;
//    }

    /**
     * 初始化方法
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init.......Person");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy......Person");
    }

}
