package com.cs4.appointmentManagement.dao.impl;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cs4.appointmentManagement.dao.PatientDao;
import com.cs4.appointmentManagement.domain.Doctor;
import com.cs4.appointmentManagement.domain.Patient;

@Repository
public class PatientDaoImpl extends GenericDaoImpl<Patient> implements PatientDao {
	public PatientDaoImpl() {
		super.setDaoType(Patient.class);
	}
	
	/**
	 * this method is used to return the total number of appointments by the user
	 * @param id
	 * @return number of appointments
	 */
	@Override
	public int totalAppointmentPast(Long id){
		Query query = entityManager.createQuery("SELECT p FROM Appointment p INNER JOIN p.patient u WHERE u.id = :id AND p.dateTime < CURRENT_DATE()");
		query.setParameter("id", id);
		return query.getResultList().size();
	}
	
	/**
	 * this method return the number of appointments by the user
	 * @param id
	 * @return number of appointments in the future
	 */
	@Override
	public int totalAppointmentFuture(Long id){
		Query query = entityManager.createQuery("SELECT p FROM Appointment p INNER JOIN p.patient u WHERE u.id = :id AND p.dateTime > CURRENT_DATE()");
		query.setParameter("id", id);
		return query.getResultList().size();
	}

	/**
	 * this method return the number of appointments by the user
	 * @param id
	 * @return number of appointments in the pasr
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Doctor> getMyDoctors(Long id) {
		Query query = entityManager.createQuery("SELECT DISTINCT p.doctor FROM Appointment p INNER JOIN p.patient u WHERE u.id = :id");
		query.setParameter("id", id);
		return (List<Doctor>) query.getResultList();
	}
} 
