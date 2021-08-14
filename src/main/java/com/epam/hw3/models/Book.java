package com.epam.hw3.models;

import com.epam.hw3.DTOs.BookDTO;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "book")
@NamedQuery(name = "Book.findAllOrderedByName",
        query = "SELECT b FROM Book b ORDER BY b.name")
public class Book {
    @Id
    @Column(name = "book_id", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id;
    String name;
    String description;
    @ManyToOne
    @JoinColumn(name="author_id", nullable=false)
    Author author;
    @ManyToMany(mappedBy = "books")
    Set<User> users;

    public Book(String name, String description, Author author) {
        this.name = name;
        this.description = description;
        this.author = author;
    }

    public Book() {
    }

    public BookDTO toDTO(){
        return new BookDTO(name, description, author.id, id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
