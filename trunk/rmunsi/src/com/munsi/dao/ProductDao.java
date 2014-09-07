package com.munsi.dao;

import java.util.List;

import com.munsi.pojo.master.Product;
import com.munsi.pojo.master.ProductBatch;

public interface ProductDao {

	public Boolean create(Product product);

	public Boolean update(Product product);

	public Boolean delete(String _id);

	/**
	 * Get Product instance, Product instance does not load references instance,
	 * 
	 * @param _id
	 *            product id to be loaded
	 * @return instance of Product
	 */
	public Product get(String _id);

	/**
	 * @param _id
	 *            product id to be loaded
	 * @param withReferences
	 *            - Boolean, if true product object load its references, if
	 *            false product object does not load its references. (default is
	 *            false)
	 * @return ArraList of products
	 */
	public Product get(String _id, Boolean withReferences);

	/**
	 * Get list of Product instance, Product instance does not load references
	 * instance,
	 * 
	 * @return ArraList of products
	 */
	public List<Product> getAll();

	/**
	 * @param withReferences
	 *            - Boolean, if true product object load its references, if
	 *            false product object does not load its references.(default is
	 *            false)
	 * @return ArraList of products
	 */
	public List<Product> getAll(Boolean withReferences);

	public Product getProductByCode(String code, Boolean withReferences, Boolean excludeExpiredBatch, Boolean excludeZeroStock);

	public Product getProductByBarCode(String barCode, Boolean withReferences, Boolean excludeExpiredBatch, Boolean excludeZeroStock);

	public Product getProductByName(String name, Boolean withReferences, Boolean excludeExpiredBatch, Boolean excludeZeroStock);

	public ProductBatch getBatchInfo(String _id, String batchNo);

	public List<ProductBatch> getBatchList(String _id);

	int getAvailableStock(String _id);

	Product getForNotification(String _id);

	List<Product> getAllForNotification();
}
