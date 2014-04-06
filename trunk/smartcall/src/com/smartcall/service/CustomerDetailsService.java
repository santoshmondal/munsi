package com.smartcall.service;

import java.util.List;
import java.util.Set;

import com.async.util.ObjectFactory;
import com.async.util.ObjectFactory.ObjectEnum;
import com.smartcall.dao.CustomerDetailsDao;
import com.smartcall.pojo.CustomerDetails;

public class CustomerDetailsService {
	private CustomerDetailsDao customerDetailsDao;
	
	public CustomerDetailsService(){
		customerDetailsDao = (CustomerDetailsDao) ObjectFactory.getInstance(ObjectEnum.CUSTOMER_DETAILS_DAO);
	}
	
	
	public Boolean create(CustomerDetails customerDetails){
		return customerDetailsDao.create(customerDetails);
	}
	
	public Boolean update(CustomerDetails customerDetails){
		return customerDetailsDao.update(customerDetails);
	}
	
	public List<CustomerDetails> getCusDetailsList(){
		return customerDetailsDao.getCusDetailsList();
	}
	
	public CustomerDetails getCustomerDetails( String _id ){
		return customerDetailsDao.getCustomerDetails(_id);
	}

	Boolean assignCaller(Set<String> vinList, String coller_xid){
		return customerDetailsDao.assignCaller(vinList, coller_xid);
	}

}
