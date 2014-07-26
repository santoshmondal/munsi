package com.munsi.service;

import java.util.List;

import com.munsi.dao.ProductGroupDao;
import com.munsi.dao.impl.MongoProductGroupDao;
import com.munsi.pojo.master.ProductGroup;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

/**
 * @author isdc
 *
 */
public class ProductGroupServeice {

	private ProductGroupDao productGroupDao;

	public ProductGroupServeice() {
		Object object = ObjectFactory.getInstance(ObjectEnum.PRODUCT_GROUP_DAO);
		if (object instanceof ProductGroupDao) {
			productGroupDao = (MongoProductGroupDao) object;
		}

	}
	
	public Boolean create(ProductGroup productGroup) {
		return productGroupDao.create(productGroup);
	}

	public Boolean update(ProductGroup productGroup) {
		return productGroupDao.update(productGroup);
	}

	public Boolean delete(String _id) {
		return productGroupDao.delete(_id);
	}

	public ProductGroup get(String _id) {
		return productGroupDao.get(_id);
	}

	public List<ProductGroup> getAll() {
		return productGroupDao.getAll();
	}
	
	
	/**
	 * @param level - level "1"means product group, "2" means product sub group
	 * @return
	 */
	public List<ProductGroup> getAll(String level) {
		return productGroupDao.getAll(level);
	}
}
