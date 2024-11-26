package com.example.MSUser.repositories;

import com.example.MSUser.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByIsHostTrue();

    List<User> findAll();

}
