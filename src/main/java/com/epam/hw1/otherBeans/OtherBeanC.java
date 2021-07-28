package com.epam.hw1.otherBeans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("OtherBeanC")
public class OtherBeanC extends OtherBean{
}
