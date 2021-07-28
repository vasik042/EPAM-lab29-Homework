package com.epam.hw1.task7;

import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Primary
@Order(1)
public class SomeBeanA implements SomeInterface {
}
