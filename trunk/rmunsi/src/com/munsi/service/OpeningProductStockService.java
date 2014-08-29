package com.munsi.service;

import java.util.List;

import com.munsi.dao.OpeningProductStockDao;
import com.munsi.pojo.master.OpeningProductStock;
import com.munsi.util.CommonUtil;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

public class OpeningProductStockService {

	private OpeningProductStockDao openingProductStock;

	public OpeningProductStockService() {
		Object object = ObjectFactory.getInstance(ObjectEnum.OPENING_PRODUCT_STOCK_DAO);
		if (object instanceof OpeningProductStockDao) {
			openingProductStock = (OpeningProductStockDao) object;
		}
	}

	public Boolean create(OpeningProductStock productOpeningStock) {
		return openingProductStock.create(productOpeningStock);
	}

	public Boolean update(OpeningProductStock productOpeningStock) {
		return openingProductStock.update(productOpeningStock);
	}

	public Boolean delete(String _id) {
		return openingProductStock.delete(_id);
	}

	/**
	 * Get OpeningProductStock instance, OpeningProductStock instance does not
	 * load references instance,
	 * 
	 * @param _id
	 *            product id to be loaded
	 * @return instance of OpeningProductStock
	 */
	public OpeningProductStock get(String _id) {
		return openingProductStock.get(_id);
	}

	/**
	 * @param _id
	 *            product id to be loaded
	 * @param withReferences
	 *            - Boolean, if true product object load its references, if
	 *            false product object does not load its references. (default is
	 *            false)
	 * @return ArraList of products
	 */
	public OpeningProductStock get(String _id, Boolean withReferences) {
		return openingProductStock.get(_id, withReferences);
	}

	/**
	 * Get list of OpeningProductStock instance, OpeningProductStock instance
	 * does not load references instance,
	 * 
	 * @return ArraList of products
	 */
	public List<OpeningProductStock> getAll() {

		List<OpeningProductStock> all = openingProductStock.getAll();
		convertDateToString(all);
		return all;
	}

	/**
	 * @param withReferences
	 *            - Boolean, if true product object load its references, if
	 *            false product object does not load its references.(default is
	 *            false)
	 * @return ArraList of products
	 */
	public List<OpeningProductStock> getAll(Boolean withReferences) {
		List<OpeningProductStock> all = openingProductStock.getAll(withReferences);
		convertDateToString(all);
		return all;
	}

	public List<OpeningProductStock> getOpeningStockByProduct(String product_id, Boolean withReferences) {
		List<OpeningProductStock> openingStockByProduct = openingProductStock.getOpeningStockByProduct(product_id, withReferences);
		convertDateToString(openingStockByProduct);
		return openingStockByProduct;
	}

	public List<OpeningProductStock> getOpeningStockByProduct(String product_id) {
		List<OpeningProductStock> openingStockByProduct = openingProductStock.getOpeningStockByProduct(product_id);
		convertDateToString(openingStockByProduct);
		return openingStockByProduct;
	}

	private void convertDateToString(List<OpeningProductStock> all) {
		for (OpeningProductStock openingProductStock : all) {
			openingProductStock.setsExpDate(CommonUtil.longToStringDate(openingProductStock.getExpDate().getTime()));
			openingProductStock.setsMfgDate(CommonUtil.longToStringDate(openingProductStock.getMfgDate().getTime()));
		}
	}
}
