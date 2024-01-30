package com.springbootproject.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springbootproject.model.User;
import com.springbootproject.service.UserService;
import com.springbootproject.web.dto.UserRegistrationDto;

import jakarta.servlet.http.HttpServletRequest;



@Controller
//@RequestMapping("/registration")
public class UserRegistrationController  {
	
	@Autowired
	private UserService userService;

	//set data in the model before handler method executes
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }
    

    @PostMapping("/loginCheck")
    public String processLoginForm(HttpServletRequest request,Model model) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User u=userService.validateLogin(email,password);
        System.out.println(u);
        if(u!=null) {
        	model.addAttribute("tables",userService.findUserTable());
        	
        	return "userTableList";
        }
        else {
        	
        	model.addAttribute("error", "Invalid username or password"); 
        	return "login";
        }
        //return "redirect:/loginCheck"; // Redirect back to the login form
    }
}
