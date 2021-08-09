package com.epam.hw3.repositories;

import com.epam.hw3.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE author SET bio = ?1, first_name = ?2, last_name = ?3 WHERE author_id = ?4", nativeQuery = true)
    void update(String bio, String firstName, String lastName, int id);
}
