package com.epam.hw3.repositories;

import com.epam.hw3.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET first_name = ?1, last_name = ?2, password = ?3, username = ?4 WHERE email = ?5", nativeQuery = true)
    void updateUser(String firstName, String lastName, String password, String username, String email);
}
