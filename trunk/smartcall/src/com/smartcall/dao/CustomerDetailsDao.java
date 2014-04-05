package com.smartcall.dao;

import java.util.List;
import java.util.Set;

import com.smartcall.pojo.CustomerDetails;

public interface CustomerDetailsDao {
	
	public Boolean create(CustomerDetails customerDetails);
	
	public Boolean update(CustomerDetails customerDetails);
	
	public List<CustomerDetails> getCusDetailsList();
	
	public CustomerDetails getCustomerDetails( String _id );

	Boolean assignCaller(Set<String> vinList, String coller_xid);
	
}