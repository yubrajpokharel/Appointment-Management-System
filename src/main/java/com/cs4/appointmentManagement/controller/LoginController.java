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


/**
 * This controller is used to perform the login and registration tasks for the frontend user
 */
@Controller
public class LoginController {

	@Autowired
	UserCredentialsServiceImpl UserCredentialsServiceImpl;

	@Autowired
	UserService userService;
	
	@Autowired
	DoctorService doctorService;

	/**
	 * This method is used to show the login page to the frontend user
	 * @return login page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage() {
		return "login";
	}

	/**
	 * This method is used to show the registration page to the user
	 * @return register page to the user
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(ModelMap model) {
		User user = new User();
		model.addAttribute("roles", UserCredentialsServiceImpl.getAllAuthorities());
		model.addAttribute("user", user);
		
		return "register";
	}

	/**
	 * This method is used to perform the registration task once the registration form is submitted
	 * @param user - contain the user details
	 * @param result - binds the users details with the user model
	 * @return the message if the registration is successful
	 */
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
