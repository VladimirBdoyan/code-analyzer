package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.example.security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    @Query("select u from User u " +
            " join fetch u.authorities" +
            " where u.username = :username")
    Optional<User> findByUsernameEager(@Param("username") String username);
}
