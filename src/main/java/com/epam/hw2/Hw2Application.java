package com.epam.hw2;

import com.epam.hw2.beans.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Hw2Application {
	public static void main(String[] args) {
		SpringApplication.run(Hw2Application.class, args);

		ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationA.class);

		BeanA beanA = (BeanA) context.getBean("beanA");
		System.out.println("\nname - " + beanA.name);
		System.out.println("value - " + beanA.value);

		BeanB beanB = (BeanB) context.getBean("beanB");
		System.out.println("\nname - " + beanB.name);
		System.out.println("value - " + beanB.value);

		BeanC beanC = (BeanC) context.getBean("beanC");
		System.out.println("\nname - " + beanC.name);
		System.out.println("value - " + beanC.value);

		BeanD beanD = (BeanD) context.getBean("beanD");
		System.out.println("\nname - " + beanD.name);
		System.out.println("value - " + beanD.value);

		BeanE beanE = (BeanE) context.getBean("beanE");
		System.out.println("\nname - " + beanE.name);
		System.out.println("value - " + beanE.value);

		BeanF beanF = (BeanF) context.getBean("beanF");
		System.out.println("\nname - " + beanF.name);
		System.out.println("value - " + beanF.value);
		System.out.println();
	}

}
