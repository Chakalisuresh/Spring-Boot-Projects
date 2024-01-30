package com.springbootproject.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.springbootproject.model.RTable;
import com.springbootproject.model.User;
import com.springbootproject.repository.RTableRepo;
import com.springbootproject.repository.UserRepository;
import com.springbootproject.web.dto.UserRegistrationDto;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private RTableRepo rTableRepo;
	

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user=new User(registrationDto.getFirstName(),registrationDto.getLastName(),
				registrationDto.getEmail(),registrationDto.getPassword());
		return userRepository.save(user);
	}
	


	@Override
	public User validateLogin(String email, String password) {
		
		User userData=null;
		Optional<User> loginDetails = userRepository.getLogin(email,password);
		
		if(loginDetails.isPresent()) {
			userData=loginDetails.get();
		
		}
		
		return userData;
		
	}



	@Override
	public List<RTable> findUserTable() {
		
		return rTableRepo.findAll();
	}
	
	   
}
