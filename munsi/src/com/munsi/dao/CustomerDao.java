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
	 * @param _id
	 *            customer id to be loaded
	 * @param withReferences
	 *            - Boolean, if true customer object load its references, if
	 *            false customer object does not load its references. (default
	 *            is false)
	 * @return ArraList of customers
	 */
	public Customer get(String _id, Boolean withReferences);

	/**
	 * Get list of Customer instance, Customer instance does not load references
	 * instance,
	 * 
	 * @return ArraList of customers
	 */
	public List<Customer> getAll();

	/**
	 * @param withReferences
	 *            - Boolean, if true customer object load its references, if
	 *            false customer object does not load its references.(default is
	 *            false)
	 * @return ArraList of customers
	 */
	public List<Customer> getAll(Boolean withReferences);

}
