package com.munsi.dao;

import java.util.List;

import com.munsi.pojo.master.ProductScheme;

public interface ProductSchemeDao {

	public Boolean create(ProductScheme scheme);

	public Boolean update(ProductScheme scheme);

	public Boolean delete(String _id);

	/**
	 * Get Scheme instance, Scheme instance does not load references
	 * instance,
	 * 
	 * @param _id
	 *            product id to be loaded
	 * @return instance of Scheme
	 */
	public ProductScheme get(String _id);

	/**
	 * @param _id
	 *            product id to be loaded
	 * @param withReferences
	 *            - Boolean, if true product object load its references, if
	 *            false product object does not load its references. (default
	 *            is false)
	 * @return ArraList of products
	 */
	public ProductScheme get(String _id, Boolean withReferences);

	/**
	 * Get list of Scheme instance, Scheme instance does not load references
	 * instance,
	 * 
	 * @return ArraList of products
	 */
	public List<ProductScheme> getAll();

	/**
	 * @param withReferences
	 *            - Boolean, if true product object load its references, if
	 *            false product object does not load its references.(default is
	 *            false)
	 * @return ArraList of products
	 */
	public List<ProductScheme> getAll(Boolean withReferences);

	public List<ProductScheme> getSchemeByProduct(String product_id,
			Boolean withReferences);

	public List<ProductScheme> getSchemeByProduct(String product_id);

	


}
