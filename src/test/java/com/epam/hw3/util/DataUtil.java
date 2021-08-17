package com.epam.hw3.util;

import com.epam.hw3.models.Author;
import com.epam.hw3.models.Book;
import com.epam.hw3.models.User;

public class DataUtil {

    public static Book createBook() {
        Author author = new Author("FirstName", "LastName", "Bio");
        author.setId(1);

        Book book = new Book();
        book.setId(1);
        book.setName("name");
        book.setDescription("description");
        book.setAuthor(author);

        return book;
    }

    public static User createUser(int i) {
        User user = new User();
        user.setId(i);
        user.setEmail("email" + i);
        user.setUsername("username" + i);
        user.setFirstName("firstName" + i);
        user.setLastName("lastname" + i);
        user.setPassword("password" + i);

        return user;
    }

    public static Author createAuthor() {
        Author author = new Author("FirstName", "LastName", "Bio");
        author.setId(1);

        return author;
    }
}
