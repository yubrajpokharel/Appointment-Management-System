package com.cs4.appointmentManagement.controller;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping(value={"/", "/index"}, method=RequestMethod.GET)
	public String getIndexPage() {
		
		return "login";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String getHomePage(HttpServletRequest request) {
		System.out.println("yuppie");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = "";
		Iterator iterator = auth.getAuthorities().iterator();
		while(iterator.hasNext()) {
			role = (String)iterator.next().toString();
			break;
		}
		
		System.out.println(role);
		
		if(role.equalsIgnoreCase("ROLE_DOCTOR")) {
			System.out.println("yuppie");
			return "redirect:/doctor/";
		} else if(role.equalsIgnoreCase("ROLE_PATIENT")) {
			System.out.println("patient");
			return "redirect:/p/";
		} else {
			return "home";
		}		
	}
}
