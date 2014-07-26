package com.munsi.service;

import java.util.List;

import com.munsi.dao.ProductSchemeDao;
import com.munsi.pojo.master.ProductScheme;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

public class ProductSchemeService {
	private ProductSchemeDao productSchemeDao;

	public ProductSchemeService() {
		Object object = ObjectFactory.getInstance(ObjectEnum.PRODUCT_SCHEME_DAO);
		if (object instanceof ProductSchemeDao) {
			productSchemeDao = (ProductSchemeDao) object;
		}

	}
	
	public Boolean create(ProductScheme scheme){
		return productSchemeDao.create(scheme);
	}

	public Boolean update(ProductScheme scheme){
		return productSchemeDao.update(scheme);
	}

	public Boolean delete(String _id){
		return productSchemeDao.delete(_id);
	}

	/**
	 * Get Scheme instance, Scheme instance does not load references
	 * instance,
	 * 
	 * @param _id
	 *            product id to be loaded
	 * @return instance of Scheme
	 */
	public ProductScheme get(String _id){
		return productSchemeDao.get(_id);
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
	public ProductScheme get(String _id, Boolean withReferences){
		return productSchemeDao.get(_id,withReferences);
	}

	/**
	 * Get list of Scheme instance, Scheme instance does not load references
	 * instance,
	 * 
	 * @return ArraList of products
	 */
	public List<ProductScheme> getAll(){
		return productSchemeDao.getAll();
	}

	/**
	 * @param withReferences
	 *            - Boolean, if true product object load its references, if
	 *            false product object does not load its references.(default is
	 *            false)
	 * @return ArraList of products
	 */
	public List<ProductScheme> getAll(Boolean withReferences){
		return productSchemeDao.getAll(withReferences);
	}

	public List<ProductScheme> getSchemeByProduct(String product_id,
			Boolean withReferences){
		return productSchemeDao.getSchemeByProduct(product_id,withReferences);
	}

	public List<ProductScheme> getSchemeByProduct(String product_id){
		return productSchemeDao.getSchemeByProduct(product_id);
	}

	

}
