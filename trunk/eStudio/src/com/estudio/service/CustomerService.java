package com.estudio.service;

import java.util.List;

import com.async.util.ObjectFactory;
import com.async.util.ObjectFactory.ObjectEnum;
import com.estudio.dao.CustomerDao;
import com.estudio.pojo.Customer;

public class CustomerService {
	private CustomerDao customerDao;
	
	public CustomerService(){
		customerDao = (CustomerDao) ObjectFactory.getInstance(ObjectEnum.CUSTOMER_DAO);
	}
	
	
	public Boolean create(Customer customer){
		return customerDao.create(customer);
	}
	
	public Boolean update(Customer customer){
		return customerDao.update(customer);
	}
	
	public List<Customer> getAll(){
		return customerDao.getAll();
	}
	
	public Customer get( String _id ){
		return customerDao.get(_id);
	}
	
	public Boolean delete(String _id){
		return customerDao.delete(_id);
	}
	
}
