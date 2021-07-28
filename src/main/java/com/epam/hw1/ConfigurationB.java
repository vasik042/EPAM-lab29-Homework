package com.epam.hw1;

import com.epam.hw1.beans3.BeanE;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {"com/epam/hw1/beans2", "com/epam/hw1/beans3"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = BeanE.class)})
public class ConfigurationB {
}
