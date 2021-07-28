package com.epam.hw1.task7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SomeBeansHolder {
    @Autowired
    public SomeInterface[] someBeans;
}
