package com.munsi.dao;

import java.util.List;

import com.munsi.pojo.master.Customer;

public interface CustomerDao {

	public Boolean create(Customer customer);

	public Boolean update(Customer customer);

	public Boolean delete(String _id);

	/**
	 * Get Customer instance, Customer instance does not load references
	 * instance,
	 * 
	 * @param _id
	 *            customer id to be loaded
	 * @return instance of Customer
	 */
	public Customer get(String _id);

	/**
	 * Get list of Customer instance, Customer instance does not load references
	 * instance,
	 * 
	 * @return ArraList of customers
	 */
	public List<Customer> getAll();


}
