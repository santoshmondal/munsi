package com.munsi.dao;

import java.util.List;

import com.munsi.pojo.master.ProductGroup;

public interface ProductGroupDao {

	public Boolean create(ProductGroup ProductGroup);

	public Boolean update(ProductGroup ProductGroup);

	public Boolean delete(String _id);

	/**
	 * Get ProductGroup instance, ProductGroup instance does not load references
	 * instance,
	 * 
	 * @param _id
	 *            ProductGroup id to be loaded
	 * @return instance of ProductGroup
	 */
	public ProductGroup get(String _id);

	
	/**
	 * Get list of ProductGroup instance, ProductGroup instance does not load
	 * references instance,
	 * 
	 * @return ArraList of ProductGroups
	 */
	public List<ProductGroup> getAll();

	/**
	 * @return List of String array, String array contains id at 0 index, and
	 *         name at 1 index
	 * @param Integer lavel :- 1-product group, 2-Product sub group
	 */

	/**
	 * Get list of ProductGroup instance, ProductGroup instance does not load
	 * references instance,
	 * 
	 * @return ArraList of ProductGroups
	 */
	public List<ProductGroup> getAll(String level);

}
