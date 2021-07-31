package com.epam.hw2.beans;

import org.springframework.beans.factory.annotation.Value;

public class BeanF {
    @Value("beanF")
    public String name;
    @Value("6")
    public int value;

    @Override
    public String toString() {
        return super.toString();
    }
}
