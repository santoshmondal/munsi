package com.smartcall.dao;

import java.util.List;
import java.util.Set;

import com.smartcall.pojo.CustomerDetails;

public interface CustomerDetailsDao {
	
	public Boolean create(CustomerDetails customerDetails);
	
	public Boolean update(CustomerDetails customerDetails);
	
	public List<CustomerDetails> getAll();
	
	public CustomerDetails get( String _id );

	public Boolean assignCaller(Set<String> vinList, String coller_xid);

	public Boolean delete(String _id);
	
}
