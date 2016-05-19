package com.cs4.appointmentManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cs4.appointmentManagement.domain.User;
import com.cs4.appointmentManagement.service.AppointmentService;
import com.cs4.appointmentManagement.service.DoctorService;
import com.cs4.appointmentManagement.service.UserService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value={"/","/list"})
	private String listDoctors(Model model) {
		model.addAttribute("listDoctors", this.doctorService.findAll());
		model.addAttribute("docId", getUserID(getPrincipal()));
		return "doctor/list";
	}
	
	@RequestMapping(value={"/profile/{doctorId}"})
	private String showProfile(Model model, @PathVariable Long doctorId) {
		model.addAttribute("doctor", this.doctorService.findOne(doctorId));
		model.addAttribute("docId", getUserID(getPrincipal()));
		return "doctor/profile";
	}
	
	@RequestMapping(value={"/profile/{doctorId}/appointment"})
	private String showAppointments(Model model, @PathVariable Long doctorId) {
		model.addAttribute("doctor", this.doctorService.findOne(doctorId));
		model.addAttribute("listAppointments", this.appointmentService.findByDoctorId(doctorId));
		model.addAttribute("docId", getUserID(getPrincipal()));
		
		return "doctor/appointments";
	}
	
	@RequestMapping(value = "/profile/{doctorId}/appointment/{appointmentId}")
	public String showAppointmentDetail(Model model, @PathVariable Long doctorId, @PathVariable Long appointmentId){
		model.addAttribute("doctor", doctorService.findOne(doctorId));
		model.addAttribute("appointment", appointmentService.findOne(appointmentId));
		model.addAttribute("docId", getUserID(getPrincipal()));
		return "doctor/appointmentDetails";
	}
	
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof User) {
			userName = ((User)principal).getFname();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	private Long getUserID(String username){
		return userService.findUserID(username);
	}
	
}
