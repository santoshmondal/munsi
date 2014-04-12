package com.estudio.dao;

import java.util.List;

import com.estudio.pojo.Customer;

public interface CustomerDao {

	public Boolean create(Customer customer);

	public Boolean update(Customer customer);

	public Boolean delete(String _id);

	public Customer get(String _id);

	public List<Customer> getAll();

}
