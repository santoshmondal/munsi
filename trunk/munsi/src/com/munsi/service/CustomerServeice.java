package com.munsi.service;

import java.util.List;

import com.munsi.dao.CustomerDao;
import com.munsi.dao.ObjectFactory;
import com.munsi.dao.ObjectFactory.ObjectEnum;
import com.munsi.pojo.master.Customer;

public class CustomerServeice {

	private CustomerDao customerDao;

	public CustomerServeice() {
		Object object = ObjectFactory.getDaoInstance(ObjectEnum.CUSTOMER_DAO);
		if (object instanceof CustomerDao) {
			customerDao = (CustomerDao) object;
		}

	}

	public Boolean create(Customer customer) {
		return customerDao.create(customer);
	}

	public Boolean update(Customer customer) {
		return customerDao.update(customer);
	}

	public Boolean delete(String _id) {
		return customerDao.delete(_id);
	}

	public Customer get(String _id) {
		return customerDao.get(_id);
	}

	public List<Customer> getAll() {
		return customerDao.getAll();
	}
}
