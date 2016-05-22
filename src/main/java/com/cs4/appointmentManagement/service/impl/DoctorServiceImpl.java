package com.cs4.appointmentManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs4.appointmentManagement.dao.DoctorDao;
import com.cs4.appointmentManagement.domain.Doctor;
import com.cs4.appointmentManagement.service.DoctorService;

/**
 * Services for the doctor
 */
@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorDao doctorDao;
	
	@Override
	public void save(Doctor doctor) {
		doctorDao.save(doctor);
		
	}

	@Override
	public void delete(Long id) {
		doctorDao.delete(id);
		
	}

	@Override
	public Doctor findOne(Long id) {
		return doctorDao.findOne(id);
	}

	/**
	 * this method is used to update the doctor info
	 * @param doctor
	 */
	@Override
	public Doctor update(Doctor doctor) {
		return doctorDao.update(doctor);
	}

	/**
	 * this method is used to list all the available doctors
	 */
	@Override
	public List<Doctor> findAll() {
		return (List<Doctor>)doctorDao.findAll();
	}
	
	
	
}
