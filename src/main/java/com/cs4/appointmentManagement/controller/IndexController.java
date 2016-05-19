package com.cs4.appointmentManagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cs4.appointmentManagement.domain.Authority;
import com.cs4.appointmentManagement.domain.Patient;
import com.cs4.appointmentManagement.domain.User;

@Controller
public class IndexController {

	@RequestMapping(value={"/", "/index"}, method=RequestMethod.GET)
	public String getIndexPage() {
		
		return "login";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String getHomePage(HttpServletRequest request) {
		System.out.println("yuppie");
		
		if(request.isUserInRole("ROLE_DOCTOR")) {
			System.out.println("yuppie");
			return "redirect:/doctor/";
		} else if(request.isUserInRole("ROLE_PATIENT")) {
			System.out.println("patient");
			return "redirect:/p/";
		} else {
			return "home";
		}		
	}
}
