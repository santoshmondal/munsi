package com.munsi.service;

import java.util.List;

import com.munsi.dao.BaseDao;
import com.munsi.dao.CustomerDao;
import com.munsi.dao.DaoFactory;
import com.munsi.dao.DaoFactory.DaoEnum;
import com.munsi.pojo.master.Customer;

public class CustomerServeice {
	
	private CustomerDao customerDao;
	
	public CustomerServeice(){
		BaseDao baseDao = DaoFactory.getDaoInstance(DaoEnum.CUSTOMER_DAO);
		if( baseDao instanceof CustomerDao ){
			customerDao = (CustomerDao) baseDao;	
		}
		
	}
	
	public Boolean create(Customer customer){
		return customerDao.create(customer);
	}
	
	public Boolean update(Customer customer){
		return customerDao.update(customer);
	}
	
	public Boolean delete(String _id){
		return customerDao.delete(_id);
	}
	
	public Customer get(String _id){
		return customerDao.get(_id);
	}
	
	public List<Customer> getAll(){
		return customerDao.getAll();		
	}
}
