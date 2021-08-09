package com.epam.hw3;

import com.epam.hw3.DTOs.UserDTO;
import com.epam.hw3.services.UserService;
import com.epam.hw3.services.imp.UserServiceImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
public class Hw3Application {
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		ConfigurableApplicationContext run = SpringApplication.run(Hw3Application.class, args);
		UserServiceImp userServiceImp = (UserServiceImp) run.getBean("userServiceImp");
		UserDTO userDTO1 = new UserDTO("Jon", "Noj", "nojon", "1");
		UserDTO userDTO2 = new UserDTO("Noj", "Jon", "jonoj", "2");
		userDTO1.password = "1";
		userDTO1.repeatPassword = "1";
		userDTO2.password = "1";
		userDTO2.repeatPassword = "1";
		userServiceImp.createUser(userDTO1);
		userServiceImp.createUser(userDTO2);
	}

}
