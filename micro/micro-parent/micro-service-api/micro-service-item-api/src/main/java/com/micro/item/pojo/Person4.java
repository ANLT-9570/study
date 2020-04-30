package com.micro.item.pojo;

import org.apache.commons.configuration.beanutils.BeanFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class Person4 implements BeanPostProcessor {

    public Person4(){
        System.out.println("p4444444444444444444444");
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization....."+beanName+"=>"+bean);
        return bean;//可对bean进行包装后返回
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("postProcessAfterInitialization....."+beanName+"=>"+bean);
        return bean;//可对bean进行包装后返回
    }
}
