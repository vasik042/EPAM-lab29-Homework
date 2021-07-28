package com.epam.hw1.task7;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Qualifier("SomeBeanB")
@Order(2)
public class SomeBeanB implements SomeInterface {
}
