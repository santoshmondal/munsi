package com.munsi.service;

import java.util.List;

import com.munsi.dao.ManufacturerDao;
import com.munsi.dao.impl.MongoManufacturerDao;
import com.munsi.pojo.master.Manufacturer;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

public class ManufacturerServeice {

	private ManufacturerDao manufacturerDao;

	public ManufacturerServeice() {
		Object object = ObjectFactory.getInstance(ObjectEnum.MANUFACTURER_DAO);
		if (object instanceof ManufacturerDao) {
			manufacturerDao = (MongoManufacturerDao) object;
		}

	}
	
	public Boolean create(Manufacturer manufacturer) {
		return manufacturerDao.create(manufacturer);
	}

	public Boolean update(Manufacturer manufacturer) {
		return manufacturerDao.update(manufacturer);
	}

	public Boolean delete(String _id) {
		return manufacturerDao.delete(_id);
	}

	public Manufacturer get(String _id) {
		return manufacturerDao.get(_id);
	}

	public List<Manufacturer> getAll() {
		return manufacturerDao.getAll();
	}
}
