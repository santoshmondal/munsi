package com.munsi.dao;

import java.util.List;

import com.munsi.pojo.master.Supplier;

public interface SupplierDao {

	public Boolean create(Supplier supplier);

	public Boolean update(Supplier supplier);

	public Boolean delete(String _id);

	/**
	 * Get Supplier instance, Supplier instance does not load references
	 * instance,
	 * 
	 * @param _id
	 *            supplier id to be loaded
	 * @return instance of Supplier
	 */
	public Supplier get(String _id);

	/**
	 * @param _id
	 *            supplier id to be loaded
	 * @param withReferences
	 *            - Boolean, if true supplier object load its references, if
	 *            false supplier object does not load its references. (default
	 *            is false)
	 * @return ArraList of suppliers
	 */
	public Supplier get(String _id, Boolean withReferences);

	/**
	 * Get list of Supplier instance, Supplier instance does not load references
	 * instance,
	 * 
	 * @return ArraList of suppliers
	 */
	public List<Supplier> getAll();

	/**
	 * @param withReferences
	 *            - Boolean, if true supplier object load its references, if
	 *            false supplier object does not load its references.(default is
	 *            false)
	 * @return ArraList of suppliers
	 */
	public List<Supplier> getAll(Boolean withReferences);

	/**
	 * @return List of String array, String array contains id at 0 index, and
	 *         name at 1 index
	 */
	List<String[]> getIdName();

}
