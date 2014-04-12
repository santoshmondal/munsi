package com.smartcall.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.smartcall.pojo.CustomerDetails;
import com.smartcall.pojo.Service;

public interface CustomerDetailsDao {
	
	public Boolean create(CustomerDetails customerDetails);
	
	public Boolean update(CustomerDetails customerDetails);
	
	public List<CustomerDetails> getAll();
	
	public CustomerDetails get( String _id );

	public Boolean assignCaller(Set<String> vinList, String coller_xid);

	public Boolean delete(String _id);

	public Boolean addService(String _id, Service service);

	public Boolean updateRating(String _id, Integer rating);

	public Boolean updateLastCalling(String _id, String lastContactedByXID,
			String lastCallResponse, String remark, Date lastCallingDate);
	
}
