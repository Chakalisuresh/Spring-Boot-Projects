package com.springbootproject.service;

import java.util.List;
import java.util.Optional;

//import org.springframework.security.core.userdetails.UserDetailsService;

import com.springbootproject.model.RTable;
import com.springbootproject.model.User;
import com.springbootproject.web.dto.UserRegistrationDto;



//public interface UserService extends UserDetailsService{
public interface UserService {
	User save(UserRegistrationDto registrationDto);

	User validateLogin(String email, String password);

	List<RTable> findUserTable();
}
 