package com.smartcall.service;

import java.util.List;
import java.util.Set;

import com.async.util.ObjectFactory;
import com.async.util.ObjectFactory.ObjectEnum;
import com.smartcall.pojo.Service;
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
	
	public List<CustomerDetails> getAll(){
		return customerDetailsDao.getAll();
	}
	
	public CustomerDetails get( String _id ){
		return customerDetailsDao.get(_id);
	}

	public Boolean assignCaller(Set<String> vinList, String coller_xid){
		return customerDetailsDao.assignCaller(vinList, coller_xid);
	}
	
	public Boolean delete(String _id){
		return customerDetailsDao.delete(_id);
	}

	public boolean addService(String _id, Service service){
		return customerDetailsDao.addService(_id, service);
	}
}
