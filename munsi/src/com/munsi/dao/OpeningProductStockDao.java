package com.munsi.dao;

import java.util.List;

import com.munsi.pojo.master.OpeningProductStock;

public interface OpeningProductStockDao {

	public Boolean create(OpeningProductStock productOpeningStock);

	public Boolean update(OpeningProductStock productOpeningStock);

	public Boolean delete(String _id);

	/**
	 * Get OpeningProductStock instance, OpeningProductStock instance does not load references
	 * instance,
	 * 
	 * @param _id
	 *            product id to be loaded
	 * @return instance of OpeningProductStock
	 */
	public OpeningProductStock get(String _id);

	/**
	 * @param _id
	 *            product id to be loaded
	 * @param withReferences
	 *            - Boolean, if true product object load its references, if
	 *            false product object does not load its references. (default
	 *            is false)
	 * @return ArraList of products
	 */
	public OpeningProductStock get(String _id, Boolean withReferences);

	/**
	 * Get list of OpeningProductStock instance, OpeningProductStock instance does not load references
	 * instance,
	 * 
	 * @return ArraList of products
	 */
	public List<OpeningProductStock> getAll();

	/**
	 * @param withReferences
	 *            - Boolean, if true product object load its references, if
	 *            false product object does not load its references.(default is
	 *            false)
	 * @return ArraList of products
	 */
	public List<OpeningProductStock> getAll(Boolean withReferences);

	public List<OpeningProductStock> getOpeningStockByProduct(String product_id,
			Boolean withReferences);

	public List<OpeningProductStock> getOpeningStockByProduct(String product_id);

	


}
