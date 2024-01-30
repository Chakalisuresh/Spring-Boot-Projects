package com.springbootproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springbootproject.model.User;




@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	
	@Query(value = "SELECT * FROM User a WHERE a.email=:email AND a.password=:passWord", nativeQuery = true)
    Optional<User> getLogin(String email, String passWord);
}
