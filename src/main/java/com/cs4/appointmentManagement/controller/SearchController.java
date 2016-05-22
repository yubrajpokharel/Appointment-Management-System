package com.cs4.appointmentManagement.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cs4.appointmentManagement.service.UserService;

/**
 * This controller class is used to manage the basic search feature in the system
 * @author yubraj
 *
 */
@Controller
public class SearchController {
	
	@Autowired
	UserService userService;
	
	/**
	 * This method is used to redirect user to the basic search page i.e search/search.jsp
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String asITis(Model model){
		return "search/search";
	}
	
	/**
	 * THis method is used to search the user according to the search text
	 * @param text - String
	 * @return search/search.jsp with the search results
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String search(Model model, @RequestParam(value = "query", required = false) String text){
		if(text != null){
			model.addAttribute("results", userService.search(text));
			return "search/search";
		}else{
			return "search/search";
		}
	}

}
