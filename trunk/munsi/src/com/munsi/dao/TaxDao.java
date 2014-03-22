package com.munsi.dao;

import java.util.List;

import com.munsi.pojo.master.Tax;

public interface TaxDao {

	public Boolean create(Tax tax);

	public Boolean update(Tax tax);

	public Boolean delete(String _id);

	/**
	 * Get Tax instance, Tax instance does not load references instance,
	 * 
	 * @param _id
	 *            TaxDao id to be loaded
	 * @return instance of Tax
	 */
	public Tax get(String _id);


	/**
	 * Get list of Tax instance, Tax instance does not load references
	 * instance,
	 * 
	 * @return ArraList of Tax
	 */
	public List<Tax> getAll();

}
