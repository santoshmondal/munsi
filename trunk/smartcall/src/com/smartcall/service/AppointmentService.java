package com.smartcall.service;

import java.util.List;

import com.async.util.ObjectFactory;
import com.async.util.ObjectFactory.ObjectEnum;
import com.smartcall.dao.AppointmentDao;
import com.smartcall.pojo.Appointment;

public class AppointmentService {
	
	private AppointmentDao appointmentDao;
	
	public AppointmentService(){
		appointmentDao = (AppointmentDao) ObjectFactory.getInstance(ObjectEnum.APPOINTMENT_DAO);
	}
	
	public Boolean create(Appointment appointment){
		return appointmentDao.create(appointment);
	}
	
	public Boolean update(Appointment appointment){
		return appointmentDao.update(appointment);
	}
	
	public List<Appointment> getAll(){
		return appointmentDao.getAll();
	}
	
	public Appointment get( String _id ){
		return appointmentDao.get(_id);
	}

	public Boolean delete(String _id){
		return appointmentDao.delete(_id);
	}

}
