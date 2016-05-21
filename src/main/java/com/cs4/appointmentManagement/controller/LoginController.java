package com.cs4.appointmentManagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cs4.appointmentManagement.domain.Doctor;
import com.cs4.appointmentManagement.domain.Patient;
import com.cs4.appointmentManagement.domain.User;
import com.cs4.appointmentManagement.service.DoctorService;
import com.cs4.appointmentManagement.service.UserService;
import com.cs4.appointmentManagement.service.impl.UserCredentialsServiceImpl;

@Controller
public class LoginController {

	@Autowired
	UserCredentialsServiceImpl UserCredentialsServiceImpl;

	@Autowired
	UserService userService;
	
	@Autowired
	DoctorService doctorService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(ModelMap model) {
		User user = new User();
		model.addAttribute("roles", UserCredentialsServiceImpl.getAllAuthorities());
		model.addAttribute("user", user);
		
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveRegister(@Valid User user, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "register";
		}
		
		userService.save(user);
		
		model.addAttribute("success", "User : " + user.getFname() + " Created Successfully!");
		return "register";

	}

}
