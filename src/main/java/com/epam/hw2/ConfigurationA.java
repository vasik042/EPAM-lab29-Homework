package com.epam.hw2;

import com.epam.hw2.beans.BeanB;
import com.epam.hw2.beans.BeanC;
import com.epam.hw2.beans.BeanD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@PropertySource("classpath:beans.properties")
@Import(ConfigurationB.class)
public class ConfigurationA {
    @Autowired
    Environment env;

    @Bean (initMethod="init", destroyMethod = "destroy")
    public BeanB beanB(){
        String name = env.getProperty("beanB.name");
        int value = Integer.parseInt(Objects.requireNonNull(env.getProperty("beanB.value")));
        return new BeanB(name, value);
    }

    @Bean (initMethod="init", destroyMethod = "destroy")
    public BeanC beanC(){
        String name = env.getProperty("beanC.name");
        int value = Integer.parseInt(Objects.requireNonNull(env.getProperty("beanC.value")));
        return new BeanC(name, value);
    }

    @Bean (initMethod="init", destroyMethod = "destroy")
    public BeanD beanD(){
        String name = env.getProperty("beanD.name");
        int value = Integer.parseInt(Objects.requireNonNull(env.getProperty("beanD.value")));
        return new BeanD(name, value);
    }
}
