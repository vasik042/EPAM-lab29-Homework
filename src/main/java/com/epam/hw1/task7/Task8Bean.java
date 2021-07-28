package com.epam.hw1.task7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Task8Bean {
    @Autowired
    public SomeInterface primaryBeanA;

    @Autowired
    @Qualifier ("SomeBeanB")
    public SomeInterface beanB;

    @Autowired
    @Qualifier ("SomeBeanC")
    public SomeInterface beanC;
}
