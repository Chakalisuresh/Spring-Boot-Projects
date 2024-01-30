package com.springbootproject.web;

import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.springbootproject.model.AdminLogin;
import com.springbootproject.service.AdminLoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AdminLoginController {

	
	 @Autowired
	  private AdminLoginService userService;
	                                   
	 @GetMapping("/AdminLogin")       
	  public ModelAndView login() {
	        ModelAndView mav = new ModelAndView("AdminLogin");
	       // mav.setView(null);
	        mav.addObject("user", new AdminLogin());
	        return mav;
	    }
	    
	    
	    @GetMapping("/AdminloginForm")
	    public String loginForm() {
	    	return "AdminLogin";
	    }
	    
	    @PostMapping("/AdminloginCheck")
	    public String loginCheck(@ModelAttribute ("user")AdminLogin login,Model model) {
	    	
	    	String name=login.getUsername();
	    	String password=login.getPassword();
	    	
	    	if(name.equalsIgnoreCase("Admin")&& password.equalsIgnoreCase("12345")) {
	    		
	    		return "redirect:/adminTableList";
	    		
	    	}
	    		model.addAttribute("error", "Invalid username or password"); 
	        	return "AdminLogin";
	    	}
	    	
	    
	    
	    
	    @RequestMapping(value = {"/AdminLogout"}, method = RequestMethod.POST)
	    public String logoutDo(HttpServletRequest request,HttpServletResponse response)
	    {
	    
	      
	        return "redirect:/AdminLogin";
	    }

}
