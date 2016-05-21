package com.cs4.appointmentManagement.controller;

import java.lang.ProcessBuilder.Redirect;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cs4.appointmentManagement.domain.Appointment;
import com.cs4.appointmentManagement.domain.Doctor;
import com.cs4.appointmentManagement.domain.Patient;
import com.cs4.appointmentManagement.domain.User;
import com.cs4.appointmentManagement.jms.MessageSender;
import com.cs4.appointmentManagement.service.AppointmentService;
import com.cs4.appointmentManagement.service.DoctorService;
import com.cs4.appointmentManagement.service.PatientService;
import com.cs4.appointmentManagement.service.UserService;

/**
 * This controller is used to control the patients
 * Here it includes all the methods that are used to manage the patient
 * @author yubraj
 *
 */
@Controller
@RequestMapping("/p")
public class PatientController {

	@Autowired
	@Qualifier("appointmentJmsSender")
	private MessageSender appointmentSender;
	
	@Autowired
	AppointmentService appointmentService;
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	DoctorService docService;
	
	/**
	 * this method is used to display the home page for the patient upon 
	 * their successful login in the system
	 * @param url (/ or /home)
	 * @return home page for the patient
	 * 
	 */
	@RequestMapping(value = {"/", "/home"})
	public String listAppointments(Model model){
		model.addAttribute("appointments", appointmentService.getAppointmentsByUserID(getUserID(getPrincipal())));
		model.addAttribute("user", getPrincipal());
		model.addAttribute("patientId", getUserID(getPrincipal()));
		System.out.println("Logged IN username: " + getPrincipal());
		System.out.println("Logged IN user ID: " + getUserID(getPrincipal()));
		return "patient/appointments";
	}
	
	/**
	 * this method is used to show the appointment details to the the patient
	 * patient must be login into the system
	 * @param Long appointmentID
	 * @return appointment details for the patient
	 * 
	 */
	@RequestMapping(value = "/a/{appointmentId}")
	public String appointmentDetail(Model model, @PathVariable Long appointmentId){
		model.addAttribute("appointment", appointmentService.findOne(appointmentId));
		model.addAttribute("patientId", getUserID(getPrincipal()));
		return "patient/appointmentDetail";
	}
	
	/**
	 * 
	 * this method is used to show the patient profile page
	 * @param patientID
	 * @return patient profile page according to the patientID 
	 */
	@RequestMapping(value="/profile/{patientID}")
	public String myProfile(Model model, @PathVariable Long patientID){
		model.addAttribute("profile", patientService.findOne(patientID));
		model.addAttribute("past", patientService.totalAppointmentPast(patientID));
		model.addAttribute("future", patientService.totalAppointmentFuture(patientID));
		model.addAttribute("doctors", patientService.getMyDoctors(patientID));
		model.addAttribute("patientId", getUserID(getPrincipal()));
		return "patient/profile";
	}
	
	/**
	 * 
	 * this method is used to book an appointment to the specific doctor
	 * @param docID
	 * @return the view for the appointment registration
	 */
	@RequestMapping(value="/bookapt/{docID}", method=RequestMethod.GET)
	public String bookAppointment(Model model, @PathVariable Long docID){
		model.addAttribute("doctor", docService.findOne(docID));
		model.addAttribute("patientId", getUserID(getPrincipal()));
		return "patient/bookAppointment";
	}
	
	
	/**
	 * 
	 * This method is used to generate the appointment once submitted the form
	 * @return the successful message after the appointment is generated to the doctor
	 * @throws ParseException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/appointment.do", method=RequestMethod.POST)
	public String saveAppointment(ModelMap model, HttpServletRequest request) throws ParseException{
		
		
		Appointment appointment = new Appointment();
		
		Patient pat = new Patient();
		pat = (Patient) userService.findByUsername(getPrincipal());
		appointment.setPatient(pat);
		appointment.setDescription(request.getParameter("desc"));
		
		String dt = request.getParameter("dateTime");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.US);
		Date date = sdf.parse(dt);
		
		appointment.setDateTime(date);
		Doctor doc = new Doctor();
		System.out.println("Doc ID:: "+request.getParameter("docid"));
		System.out.println(Integer.parseInt(request.getParameter("docid")));
		doc = (Doctor) userService.findUserByID(Long.parseLong(request.getParameter("docid"), 10));
		
		appointment.setDoctor(doc);

		appointmentSender.sendMessage(appointment);
		appointmentService.save(appointment);
		model.addAttribute("patientId", getUserID(getPrincipal()));
		model.addAttribute("response", "Sucessfully send Appointment request to Dr. "+doc.getFname() + " " +doc.getLastName()+" on "+date.toString());
		
		return "patient/appointmentSuccess";
	}
	
	
	/**
	 * This method is used to get the currently logged in username
	 * @return logged in userName
	 */
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
		/**
		 * This method will return the user ID
		 * @param username
		 * @return userID
		 */
		private Long getUserID(String username){
			return userService.findUserID(username);
		}


	    
	

}