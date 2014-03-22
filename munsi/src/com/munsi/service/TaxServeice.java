package com.munsi.service;

import java.util.List;

import com.munsi.dao.TaxDao;
import com.munsi.dao.impl.MongoTaxDao;
import com.munsi.pojo.master.Tax;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

public class TaxServeice {

	private TaxDao taxDao;

	public TaxServeice() {
		Object object = ObjectFactory.getDaoInstance(ObjectEnum.TAX_DAO);
		if (object instanceof TaxDao) {
			taxDao = (MongoTaxDao) object;
		}

	}
	
	public Boolean create(Tax tax) {
		return taxDao.create(tax);
	}

	public Boolean update(Tax tax) {
		return taxDao.update(tax);
	}

	public Boolean delete(String _id) {
		return taxDao.delete(_id);
	}

	public Tax get(String _id) {
		return taxDao.get(_id);
	}

	public List<Tax> getAll() {
		return taxDao.getAll();
	}
}
