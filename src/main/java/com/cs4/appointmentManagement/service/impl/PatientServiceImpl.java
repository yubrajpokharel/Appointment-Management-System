package com.cs4.appointmentManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs4.appointmentManagement.dao.PatientDao;
import com.cs4.appointmentManagement.domain.Doctor;
import com.cs4.appointmentManagement.domain.Patient;
import com.cs4.appointmentManagement.service.PatientService;

@Service
@Transactional
public class PatientServiceImpl implements PatientService{

	@Autowired
	private PatientDao patientDao;
	@Override
	public void save(Patient patient) {
		patientDao.save(patient);
	}

	@Override
	public void delete(Long id) {
		patientDao.delete(id);
	}

	@Override
	public Patient findOne(Long id) {
		return patientDao.findOne(id);
	}

	/**
	 * This method is used to update the patient info
	 * @param patient
	 */
	@Override
	public Patient update(Patient patient) {
		return patientDao.update(patient);
	}
	
	/**
	 * This method is used to list all the patient
	 * @return patient list
	 */
	@Override
	public List<Patient> findAll() {
		return (List<Patient>)patientDao.findAll();
	}

	/**
	 * This method is used to find the patient past appointments number
	 * @param patient ID
	 * @return no. of appointments in the past 
	 */
	@Override
	public int totalAppointmentPast(Long id) {
		return patientDao.totalAppointmentPast(id);
	}

	/**
	 * This method is used to find the patient coming appointments number
	 * @param patient ID
	 * @return no. of appointments in the future 
	 */
	@Override
	public int totalAppointmentFuture(Long id) {
		return patientDao.totalAppointmentFuture(id);
	}

	/**
	 * This method is used to list all the doctors to whom patients have taken appointment
	 * @param id - Long
	 * @return list of Doctors
	 */
	@Override
	public List<Doctor> getMyDoctors(Long id) {
		return patientDao.getMyDoctors(id);
	}
}
