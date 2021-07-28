package com.epam.hw1.otherBeansHolders;

import com.epam.hw1.otherBeans.OtherBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OtherBeanHolderB {
    public OtherBean otherBeanB;

    @Autowired
    @Qualifier("OtherBeanB")
    public void setOtherBeanB(OtherBean otherBeanB) {
        this.otherBeanB = otherBeanB;
    }
}
