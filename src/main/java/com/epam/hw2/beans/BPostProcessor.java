package com.epam.hw2.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.length() == 5){
            try {
                String name = (String) bean.getClass().getDeclaredField("name").get(bean);
                Integer value = (Integer) bean.getClass().getDeclaredField("value").get(bean);
                if(name.isEmpty()){
                    throw new RuntimeException("Name can't be empty");
                }
                if(value < 0){
                    throw new RuntimeException("Value can't be negative");
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
