package com.cs4.appointmentManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs4.appointmentManagement.dao.AppointmentDao;
import com.cs4.appointmentManagement.domain.Appointment;
import com.cs4.appointmentManagement.jms.MessageSender;
import com.cs4.appointmentManagement.service.AppointmentService;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

	
	
	@Autowired
	private AppointmentDao appointmentDao;

	@Override
	public void save(Appointment appointment) {
		appointmentDao.save(appointment);

	}

	@Override
	public void delete(Long id) {
		appointmentDao.delete(id);

	}

	@Override
	public Appointment findOne(Long id) {
		return appointmentDao.findOne(id);
	}

	@Override
	public Appointment update(Appointment appointment) {
		// TODO Auto-generated method stub
		return appointmentDao.update(appointment);
	}

	@Override
	public List<Appointment> findAll() {
		// TODO Auto-generated method stub
		return (List<Appointment>) appointmentDao.findAll();
	}

	/**
	 * this method is used to find all the appointments related to certain doctors
	 * @param id
	 * @return list of appointments
	 */
	@Override
	public List<Appointment> findByDoctorId(Long id) {
		return (List<Appointment>) appointmentDao.findByDoctorId(id);
	}

	/**
	 * this method is used to display all the appointments of the patients
	 * @param id
	 * @return the list of appointments
	 */
	@Override
	public List<Appointment> getAppointmentsByUserID(Long id) {
		return (List<Appointment>) appointmentDao.getAppointmentsByUserID(id);

	}

}
