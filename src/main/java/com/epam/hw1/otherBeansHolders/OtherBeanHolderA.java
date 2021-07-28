package com.epam.hw1.otherBeansHolders;

import com.epam.hw1.otherBeans.OtherBeanA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OtherBeanHolderA {
    public OtherBeanA otherBeanA;

    @Autowired
    public OtherBeanHolderA(OtherBeanA otherBeanA) {
        this.otherBeanA = otherBeanA;
    }
}
