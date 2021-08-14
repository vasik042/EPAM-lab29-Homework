package com.epam.hw3;

import com.epam.hw3.DTOs.AuthorDTO;
import com.epam.hw3.DTOs.BookDTO;
import com.epam.hw3.DTOs.UserDTO;
import com.epam.hw3.controllers.models.AuthorModel;
import com.epam.hw3.models.Author;
import com.epam.hw3.services.imp.AuthorServiceImp;
import com.epam.hw3.services.imp.BookServiceImp;
import com.epam.hw3.services.imp.UserServiceImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.TimeZone;

@SpringBootApplication
public class Hw3Application {
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		ConfigurableApplicationContext run = SpringApplication.run(Hw3Application.class, args);

		UserServiceImp userServiceImp = (UserServiceImp) run.getBean("userServiceImp");
		UserDTO userDTO1 = new UserDTO("Jon", "Noj", "nojon", "1");
		UserDTO userDTO2 = new UserDTO("Noj", "Jon", "jonoj", "2");
		UserDTO userDTO3 = new UserDTO("asdasd", "sdsd", "sadas", "3");
		userDTO1.password = "1";
		userDTO3.password = "1";
		userDTO2.password = "1";
		userDTO1.repeatPassword = "1";
		userDTO2.repeatPassword = "1";
		userDTO3.repeatPassword = "1";
		userServiceImp.createUser(userDTO1);
		userServiceImp.createUser(userDTO2);
		userServiceImp.createUser(userDTO3);

		AuthorServiceImp authorServiceImp = (AuthorServiceImp) run.getBean("authorServiceImp");
		AuthorDTO authorDTO1 = new AuthorDTO("Author Z", "1", "someBio");
		AuthorDTO authorDTO2 = new AuthorDTO("Author A", "2", null);
		AuthorModel author1 = authorServiceImp.createAuthor(authorDTO1);
		authorServiceImp.createAuthor(authorDTO2);

		BookServiceImp bookServiceImp = (BookServiceImp) run.getBean("bookServiceImp");

		BookDTO bookDTO1 = new BookDTO("Book1", "veryInteresting", author1.getAuthorDTO().id);
		bookServiceImp.createBook(bookDTO1);
	}

}
