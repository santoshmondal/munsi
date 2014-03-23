package com.munsi.service;

import java.util.List;

import com.munsi.dao.ProductDao;
import com.munsi.dao.impl.MongoProductDao;
import com.munsi.pojo.master.Product;
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

	public Boolean delete(String _id) {
		return productDao.delete(_id);
	}

	public Product get(String _id) {
		return productDao.get(_id);
	}

	public List<Product> getAll() {
		return productDao.getAll();
	}
}
