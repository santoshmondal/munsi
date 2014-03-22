package com.munsi.service;

import java.util.List;

import com.munsi.dao.OpeningProductStockDao;
import com.munsi.pojo.master.OpeningProductStock;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

public class OpeningProductStockService {
	
	private OpeningProductStockDao openingProductStock;
	public OpeningProductStockService() {
		Object object = ObjectFactory.getDaoInstance(ObjectEnum.OPENING_PRODUCT_STOCK_DAO);
		if (object instanceof OpeningProductStockDao) {
			openingProductStock = (OpeningProductStockDao) object;
		}
	}
	
	
	public Boolean create(OpeningProductStock productOpeningStock){
		return openingProductStock.create(productOpeningStock);
	}

	public Boolean update(OpeningProductStock productOpeningStock){
		return openingProductStock.update(productOpeningStock);
	}

	public Boolean delete(String _id){
		return openingProductStock.delete(_id);
	}

	/**
	 * Get OpeningProductStock instance, OpeningProductStock instance does not load references
	 * instance,
	 * 
	 * @param _id
	 *            product id to be loaded
	 * @return instance of OpeningProductStock
	 */
	public OpeningProductStock get(String _id){
		return openingProductStock.get(_id);
	}

	/**
	 * @param _id
	 *            product id to be loaded
	 * @param withReferences
	 *            - Boolean, if true product object load its references, if
	 *            false product object does not load its references. (default
	 *            is false)
	 * @return ArraList of products
	 */
	public OpeningProductStock get(String _id, Boolean withReferences){
		return openingProductStock.get(_id, withReferences);
	}

	/**
	 * Get list of OpeningProductStock instance, OpeningProductStock instance does not load references
	 * instance,
	 * 
	 * @return ArraList of products
	 */
	public List<OpeningProductStock> getAll(){
		return openingProductStock.getAll();
	}

	/**
	 * @param withReferences
	 *            - Boolean, if true product object load its references, if
	 *            false product object does not load its references.(default is
	 *            false)
	 * @return ArraList of products
	 */
	public List<OpeningProductStock> getAll(Boolean withReferences){
		return openingProductStock.getAll(withReferences);
	}

	public List<OpeningProductStock> getOpeningStockByProduct(String product_id,
			Boolean withReferences){
		return openingProductStock.getOpeningStockByProduct(product_id,withReferences);
	}

	public List<OpeningProductStock> getOpeningStockByProduct(String product_id){
		return openingProductStock.getOpeningStockByProduct(product_id);
	}

	
}
