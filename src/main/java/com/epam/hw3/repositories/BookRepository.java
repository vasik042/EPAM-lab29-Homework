package com.epam.hw3.repositories;

import com.epam.hw3.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE book SET name = ?1, description = ?2, author_id = ?3 WHERE book_id = ?4", nativeQuery = true)
    void updateBook(String name, String description, int author, int id);
}
