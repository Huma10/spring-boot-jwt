package com.tutorial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByLogin(String login);
}
