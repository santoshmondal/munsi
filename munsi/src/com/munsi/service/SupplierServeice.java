package com.munsi.service;

import java.util.List;

import com.munsi.dao.SupplierDao;
import com.munsi.dao.impl.MongoSupplierDao;
import com.munsi.pojo.master.Supplier;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

public class SupplierServeice {

	private SupplierDao supplierDao;

	public SupplierServeice() {
		Object object = ObjectFactory.getDaoInstance(ObjectEnum.SUPPLIER_DAO);
		if (object instanceof SupplierDao) {
			supplierDao = (MongoSupplierDao) object;
		}

	}
	
	public Boolean create(Supplier supplier) {
		return supplierDao.create(supplier);
	}

	public Boolean update(Supplier supplier) {
		return supplierDao.update(supplier);
	}

	public Boolean delete(String _id) {
		return supplierDao.delete(_id);
	}

	public Supplier get(String _id) {
		return supplierDao.get(_id);
	}

	public Supplier get(String _id,Boolean withReferences) {
		return supplierDao.get(_id,withReferences);
	}

	
	public List<Supplier> getAll() {
		return supplierDao.getAll();
	}
	
	public List<Supplier> getAll(Boolean withReferences) {
		return supplierDao.getAll(withReferences);
	}
}
