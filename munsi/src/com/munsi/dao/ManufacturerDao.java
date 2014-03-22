package com.munsi.dao;

import java.util.List;

import com.munsi.pojo.master.Manufacturer;

public interface ManufacturerDao {

	public Boolean create(Manufacturer Manufacturer);

	public Boolean update(Manufacturer Manufacturer);

	public Boolean delete(String _id);

	/**
	 * Get Manufacturer instance, Manufacturer instance does not load references
	 * instance,
	 * 
	 * @param _id
	 *            Manufacturer id to be loaded
	 * @return instance of Manufacturer
	 */
	public Manufacturer get(String _id);


	/**
	 * Get list of Manufacturer instance, Manufacturer instance does not load
	 * references instance,
	 * 
	 * @return ArraList of Manufacturers
	 */
	public List<Manufacturer> getAll();


}
