package com.epam.hw1;

import com.epam.hw1.otherBeans.OtherBeanA;
import com.epam.hw1.otherBeans.OtherBeanB;
import com.epam.hw1.otherBeans.OtherBeanC;
import com.epam.hw1.otherBeansHolders.OtherBeanHolderA;
import com.epam.hw1.otherBeansHolders.OtherBeanHolderB;
import com.epam.hw1.otherBeansHolders.OtherBeanHolderC;
import com.epam.hw1.task7.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class Hw1Application {

	public static void main(String[] args) {
		SpringApplication.run(Hw1Application.class, args);

		System.out.println("\n//Task 1 - 4");

		ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationA.class);
		System.out.println("ConfigurationA beans:");
		for (String s : context.getBeanDefinitionNames()) {
			if(s.contains(".")){
				continue;
			}
			System.out.println(s);
		}

		context = new AnnotationConfigApplicationContext(ConfigurationB.class);
		System.out.println("\nConfigurationB beans:");
		for (String s : context.getBeanDefinitionNames()) {
			if(s.contains(".")){
				continue;
			}
			System.out.println(s);
		}

		System.out.println("\n//Task 5 - 6");

		context = new AnnotationConfigApplicationContext(ConfigurationC.class);

		System.out.println("\nBeanA: " + context.getBean(OtherBeanA.class));
		System.out.println("BeanB: " + context.getBean(OtherBeanB.class));
		System.out.println("BeanC: " + context.getBean(OtherBeanC.class));

		System.out.println("\nBean: " + context.getBean(OtherBeanHolderA.class) +
				" with BeanA " + context.getBean(OtherBeanHolderA.class).otherBeanA +
				" (@Autowired constructor)");
		System.out.println("Bean: " + context.getBean(OtherBeanHolderB.class) +
				" with BeanB " + context.getBean(OtherBeanHolderB.class).otherBeanB +
				" (@Autowired setter with @Qualifier)");
		System.out.println("Bean: " + context.getBean(OtherBeanHolderC.class) +
				" with BeanC " + context.getBean(OtherBeanHolderC.class).otherBeanC +
				" (@Autowired field with @Qualifier)");

		System.out.println("\n//Task 7");

		System.out.println("\nBeanA: " + context.getBean(SomeBeanA.class) + " (Order 1)");
		System.out.println("BeanB: " + context.getBean(SomeBeanB.class) + " (Order 2)");
		System.out.println("BeanC: " + context.getBean(SomeBeanC.class) + " (Order 3)");

		System.out.println("\nBeanHolder: " + context.getBean(SomeBeansHolder.class));
		System.out.println("Collection of beans: " + Arrays.toString(context.getBean(SomeBeansHolder.class).someBeans));

		System.out.println("\n//Task 8");

		System.out.println("\nBeanA: " + context.getBean(SomeBeanA.class) + " (@Primary)");
		System.out.println("BeanB: " + context.getBean(SomeBeanB.class) + " (@Qualifier)");
		System.out.println("BeanC: " + context.getBean(SomeBeanC.class) + " (@Qualifier)");

		System.out.println("\nBeanHolder: " + context.getBean(Task8Bean.class));
		System.out.println("BeanA with @Primary: " + context.getBean(Task8Bean.class).primaryBeanA);
		System.out.println("BeanB with @Qualifier: " + context.getBean(Task8Bean.class).beanB);
		System.out.println("BeanC with @Qualifier: " + context.getBean(Task8Bean.class).beanC);
	}
}
