package com.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registration.entities.User;

@Repository					//creating UserRepository extending from JpaRepository this to write queries by using method names
								
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String username);
}