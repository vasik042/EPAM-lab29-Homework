package com.epam.hw2.beans;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class BeanE {
    @Value("beanE")
    public String name;
    @Value("5")
    public int value;

    @PostConstruct
    private void postConstruct(){
        System.out.println("Inside BeanE postConstruct method");
    }

    @PreDestroy
    private void preDestroy(){
        System.out.println("Inside BeanE preDestroy method");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
