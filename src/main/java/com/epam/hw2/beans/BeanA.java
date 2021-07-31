package com.epam.hw2.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

public class BeanA implements InitializingBean, DisposableBean {
    @Value("beanA")
    public String name;
    @Value("1")
    public int value;

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Inside BeanA destroy method");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Inside BeanA afterPropertiesSet method");
    }
}
