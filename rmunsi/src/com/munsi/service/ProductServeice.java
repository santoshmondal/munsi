package com.munsi.service;

import java.util.List;
import java.util.Set;

import com.munsi.dao.ProductDao;
import com.munsi.dao.impl.MongoProductDao;
import com.munsi.pojo.master.Product;
import com.munsi.pojo.master.Tax;
import com.munsi.util.CommonUtil;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

public class ProductServeice {

	private ProductDao productDao;

	public ProductServeice() {
		Object object = ObjectFactory.getInstance(ObjectEnum.PRODUCT_DAO);
		if (object instanceof ProductDao) {
			productDao = (MongoProductDao) object;
		}

	}

	public Boolean create(Product product) {
		return productDao.create(product);
	}

	public Boolean update(Product product) {
		return productDao.update(product);
	}

	public Boolean updateAll(Set<? extends Product> productList) {
		Boolean sReturn = false;
		try {
			for (Product product : productList) {
				productDao.update(product);
			}

			sReturn = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sReturn;
	}

	public Boolean delete(String _id) {
		return productDao.delete(_id);
	}

	public Product get(String _id) {
		return productDao.get(_id);
	}

	public List<Product> getAll() {
		return productDao.getAll();
	}

	public String getProductByCode(String code, Boolean withReferences, Boolean excludeExpiredBatch, Boolean excludeZeroStock) {
		Product product = productDao.getProductByCode(code, withReferences, excludeExpiredBatch, excludeZeroStock);
		return populateDerivedProductInfo(product);
	}

	public String getProductByBarCode(String barCode, Boolean withReferences, Boolean excludeExpiredBatch, Boolean excludeZeroStock) {
		Product product = productDao.getProductByBarCode(barCode, withReferences, excludeExpiredBatch, excludeZeroStock);
		return populateDerivedProductInfo(product);
	}

	public String getProductByName(String name, Boolean withReferences, Boolean excludeExpiredBatch, Boolean excludeZeroStock) {
		Product product = productDao.getProductByName(name, withReferences, excludeExpiredBatch, excludeZeroStock);
		return populateDerivedProductInfo(product);
	}

	private String populateDerivedProductInfo(Product product) {
		String productJson = null;
		if (product != null) {

			// populate tax
			Float derSumOfProudctTax = 0.0f;
			if (product.getTaxList() != null) {
				for (Tax tax : product.getTaxList()) {
					if (tax.getRate() != null) {
						try {
							Float taxRate = Float.valueOf(tax.getRate());
							derSumOfProudctTax += taxRate;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			// populate tax done

			product.setDerSumOfProudctTax(derSumOfProudctTax);

			// convert to json
			productJson = CommonUtil.objectToJson(product);
		}

		return productJson;
	}

	public int getAvailableStock(String _id) {
		return productDao.getAvailableStock(_id);
	}

	public static void main(String[] args) {
		//ProductServeice ref = (ProductServeice) ObjectFactory.getInstance(ObjectEnum.PRODUCT_SERVICE);
		//System.out.println(ref.getProductByName("Amul Milk", true));
	}
}
