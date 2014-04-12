package com.smartcall.dao;

import java.util.List;

import com.async.util.Constants.UIOperationsEnum;
import com.smartcall.pojo.Appointment;

public interface AppointmentDao {
	
	public Boolean create(Appointment customerDetails);
	
	public Boolean update(Appointment customerDetails);
	
	public List<Appointment> getAll();
	
	public Appointment get( String _id );

	Boolean delete(String _id);

	Boolean updateStatus(String _id, UIOperationsEnum pUIOperationsEnum);
	
}
