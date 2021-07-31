package com.epam.hw2;

import com.epam.hw2.beans.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ConfigurationB {

    @Bean
    public BeanA beanA(){
        return new BeanA();
    }

    @Bean
    public BeanE beanE(){
        return new BeanE();
    }

    @Bean
    @Lazy
    public BeanF beanF(){
        return new BeanF();
    }

    @Bean
    public BFPostProcessor bFPostProcessor(){
        return new BFPostProcessor();
    }

    @Bean
    public BPostProcessor bPostProcessor(){
        return new BPostProcessor();
    }
}
