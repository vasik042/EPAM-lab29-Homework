package com.epam.hw1.otherBeans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("OtherBeanB")
public class OtherBeanB extends OtherBean{
}
