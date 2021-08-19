package com.epam.hw3;

import com.epam.hw3.DTOs.AuthorDTO;
import com.epam.hw3.DTOs.BookDTO;
import com.epam.hw3.DTOs.UserDTO;
import com.epam.hw3.controllers.models.AuthorModel;
import com.epam.hw3.controllers.models.BookModel;
import com.epam.hw3.controllers.models.UserModel;
import com.epam.hw3.util.DataUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Profile;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Profile("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Hw1ApplicationTests {

	@Value("http://localhost:${local.server.port}/")
	private String url;

	@Autowired
	private TestRestTemplate testRestTemplate;

	//Users test

	@Test
	void createUserTest() {
		String email = "email1@gmail.com";
		UserModel userModel = addUserToDB(email);

		assertEquals(email, userModel.getUserDTO().email);

		deleteUserFromDB(email);
	}

	@Test
	void getUserTest() {
		String email = "email2@gmail.com";
		addUserToDB(email);

		UserModel userModel = testRestTemplate.getForObject(url + "/users/" + email, UserModel.class);

		assertEquals(email, userModel.getUserDTO().email);

		deleteUserFromDB(email);
	}

	@Test
	void getAllUsersSortedByNameTest() {
		String email1 = "email3@gmail.com";
		String email2 = "email4@gmail.com";
		addUserToDB(email1);
		addUserToDB(email2);

		UserModel[] users = testRestTemplate.getForObject(url + "/users/all", UserModel[].class);

		assertEquals(email1, users[0].getUserDTO().email);
		assertEquals(email2, users[1].getUserDTO().email);

		deleteUserFromDB(email1);
		deleteUserFromDB(email2);
	}

	@Test
	void getTwoUsersTest() {
		String email1 = "email5@gmail.com";
		String email2 = "email6@gmail.com";
		String email3 = "email7@gmail.com";
		addUserToDB(email1);
		addUserToDB(email2);
		addUserToDB(email3);

		UserModel[] users = testRestTemplate.getForObject(url + "/users/page=1", UserModel[].class);

		assertEquals(users.length, 2);
		assertEquals(email1, users[0].getUserDTO().email);
		assertEquals(email2, users[1].getUserDTO().email);

		deleteUserFromDB(email1);
		deleteUserFromDB(email2);
		deleteUserFromDB(email3);
	}

	@Test
	void updateUserTest() {
		String email = "email8@gmail.com";
		String username = "newUsername";
		UserDTO userDTO = addUserToDB(email).getUserDTO();
		userDTO.setPassword("AAAaaa111");
		userDTO.setRepeatPassword("AAAaaa111");
		userDTO.setUsername(username);

		testRestTemplate.put(url + "/users/" + email, userDTO);
		UserModel userModel = testRestTemplate.getForObject(url + "/users/" + email, UserModel.class);

		assertEquals(username, userModel.getUserDTO().username);

		deleteUserFromDB(email);
	}

	@Test
	void deleteUserTest() {
		String email = "email9@gmail.com";
		addUserToDB(email);

		UserModel userModel = testRestTemplate.getForObject(url + "/users/" + email, UserModel.class);

		assertEquals(email, userModel.getUserDTO().email);

		deleteUserFromDB(email);

		userModel = testRestTemplate.getForObject(url + "/users/" + email, UserModel.class);

		assertNull(userModel.getUserDTO().email);
	}

	//Authors test

	@Test
	void createAuthorTest(){
		String name = "author";
		AuthorModel authorModel = addAuthorToDB(name);

		assertEquals(name, authorModel.getAuthorDTO().firstName);

		deleteAuthorFromDB(authorModel.getAuthorDTO().id);
	}


	@Test
	void getAuthorTest(){
		String name = "author";
		AuthorModel authorModel = addAuthorToDB(name);

		authorModel = testRestTemplate.getForObject(url + "/authors/" + authorModel.getAuthorDTO().getId(), AuthorModel.class);

		assertEquals(name, authorModel.getAuthorDTO().firstName);

		deleteAuthorFromDB(authorModel.getAuthorDTO().id);
	}

	@Test
	void getAllAuthorsTest(){
		String name1 = "author1";
		String name2 = "author2";
		AuthorModel authorModel1 = addAuthorToDB(name1);
		AuthorModel authorModel2 = addAuthorToDB(name2);

		AuthorModel[] authorModels = testRestTemplate.getForObject(url + "/authors/all", AuthorModel[].class);

		assertEquals(name1, authorModels[0].getAuthorDTO().firstName);
		assertEquals(name2, authorModels[1].getAuthorDTO().firstName);

		deleteAuthorFromDB(authorModels[0].getAuthorDTO().id);
		deleteAuthorFromDB(authorModels[1].getAuthorDTO().id);
	}

	@Test
	void updateAuthorTest(){
		String name = "author";
		AuthorDTO authorDTO = addAuthorToDB(name).getAuthorDTO();

		name = "otherName";
		authorDTO.setFirstName(name);

		testRestTemplate.put(url + "/authors/" + authorDTO.id, authorDTO);
		AuthorModel authorModel = testRestTemplate.getForObject(url + "/authors/" + authorDTO.getId(), AuthorModel.class);

		assertEquals(name, authorModel.getAuthorDTO().firstName);

		deleteAuthorFromDB(authorModel.getAuthorDTO().id);
	}

	@Test
	void deleteAuthorTest(){
		String name = "author";
		AuthorModel authorModel = addAuthorToDB(name);
		authorModel = testRestTemplate.getForObject(url + "/authors/" + authorModel.getAuthorDTO().id, AuthorModel.class);

		assertEquals(name, authorModel.getAuthorDTO().firstName);

		deleteAuthorFromDB(authorModel.getAuthorDTO().id);
		authorModel = testRestTemplate.getForObject(url + "/authors/" + authorModel.getAuthorDTO().id, AuthorModel.class);

		assertNull(authorModel.getAuthorDTO().firstName);
	}

	//Books test

	@Test
	void createBookTest(){
		String name = "author";
		AuthorModel authorModel = addAuthorToDB(name);
		BookModel bookModel = addBookToDB(authorModel.getAuthorDTO().getId());

		assertEquals(bookModel.getBookDTO().authorId, authorModel.getAuthorDTO().id);

		deleteBookFromDB(bookModel.getBookDTO().id);
		deleteAuthorFromDB(authorModel.getAuthorDTO().id);
	}


	@Test
	void getBookTest(){
		String name = "author";
		AuthorModel authorModel = addAuthorToDB(name);
		BookModel bookModel = addBookToDB(authorModel.getAuthorDTO().getId());

		bookModel = testRestTemplate.getForObject(url + "/books/" + bookModel.getBookDTO().id, BookModel.class);

		assertEquals(bookModel.getBookDTO().authorId, authorModel.getAuthorDTO().id);

		deleteBookFromDB(bookModel.getBookDTO().id);
		deleteAuthorFromDB(authorModel.getAuthorDTO().id);
	}

	@Test
	void getAllBooksTest(){
		String name = "author";
		AuthorModel authorModel = addAuthorToDB(name);

		BookModel bookModel1 = addBookToDB(authorModel.getAuthorDTO().getId());
		BookModel bookModel2 = addBookToDB(authorModel.getAuthorDTO().getId());

		BookModel[] bookModels = testRestTemplate.getForObject(url + "/books/all", BookModel[].class);

		assertEquals(bookModel1.getBookDTO().id, bookModels[0].getBookDTO().id);
		assertEquals(bookModel2.getBookDTO().id, bookModels[1].getBookDTO().id);

		deleteBookFromDB(bookModel1.getBookDTO().id);
		deleteBookFromDB(bookModel2.getBookDTO().id);
		deleteAuthorFromDB(authorModel.getAuthorDTO().id);
	}

	@Test
	void updateBookTest(){
		String name = "author";
		AuthorModel authorModel = addAuthorToDB(name);
		BookDTO bookDTO = addBookToDB(authorModel.getAuthorDTO().getId()).getBookDTO();

		bookDTO.setName(name);

		testRestTemplate.put(url + "/books/" + bookDTO.id, bookDTO);
		BookModel bookModel = testRestTemplate.getForObject(url + "/books/" + bookDTO.id, BookModel.class);

		assertEquals(bookDTO.name, bookModel.getBookDTO().name);

		deleteBookFromDB(bookModel.getBookDTO().id);
		deleteAuthorFromDB(authorModel.getAuthorDTO().id);
	}

	@Test
	void deleteBookTest(){
		String name = "author";
		AuthorModel authorModel = addAuthorToDB(name);
		BookModel bookModel = addBookToDB(authorModel.getAuthorDTO().getId());

		assertEquals(bookModel.getBookDTO().authorId, authorModel.getAuthorDTO().id);

		deleteBookFromDB(bookModel.getBookDTO().id);
		deleteAuthorFromDB(authorModel.getAuthorDTO().id);

		bookModel = testRestTemplate.getForObject(url + "/books/" + bookModel.getBookDTO().id, BookModel.class);

		assertNull(bookModel.getBookDTO().name);
	}

	private UserModel addUserToDB(String email){
		UserDTO user = DataUtil.createUser(1).toDTO();
		user.setEmail(email);
		user.setPassword("AAA111aaa");
		user.setRepeatPassword("AAA111aaa");

		return testRestTemplate.postForObject(url + "users/", user, UserModel.class);
	}

	private void deleteUserFromDB(String email){
		testRestTemplate.delete(url + "users/" + email);
	}

	private BookModel addBookToDB(int authorId){
		BookDTO bookDTO = new BookDTO("book" + authorId, "veryInteresting", authorId);

		return testRestTemplate.postForObject(url + "books/", bookDTO, BookModel.class);
	}

	private void deleteBookFromDB(int id){
		testRestTemplate.delete(url + "books/" + id);
	}

	private AuthorModel addAuthorToDB(String name){
		AuthorDTO authorDTO = new AuthorDTO(name, name, name);

		return testRestTemplate.postForObject(url + "authors/", authorDTO, AuthorModel.class);
	}

	private void deleteAuthorFromDB(int id){
		testRestTemplate.delete(url + "authors/" + id);
	}
}
