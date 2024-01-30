package com.springbootproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootproject.model.AdminLogin;
import com.springbootproject.repository.AdminLoginRepository;

@Service
public class AdminLoginService {

	
	@Autowired
    private AdminLoginRepository repo;
  
  public AdminLogin login(String username, String password) {
      AdminLogin user = repo.findByUsernameAndPassword(username, password);
      return user;
  }
}
