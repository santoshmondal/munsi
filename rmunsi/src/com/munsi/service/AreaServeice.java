package com.munsi.service;

import java.util.List;

import com.munsi.dao.AreaDao;
import com.munsi.dao.impl.MongoAreaDao;
import com.munsi.pojo.master.Area;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

public class AreaServeice {

	private AreaDao areaDao;

	public AreaServeice() {
		Object object = ObjectFactory.getInstance(ObjectEnum.AREA_DAO);
		if (object instanceof AreaDao) {
			areaDao = (MongoAreaDao) object;
		}
		else{
			try {
				throw new Exception("Problem to initialise MongoAreaDao");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public Boolean create(Area area) {
		return areaDao.create(area);
	}

	public Boolean update(Area area) {
		return areaDao.update(area);
	}

	public Boolean delete(String _id) {
		return areaDao.delete(_id);
	}

	public Area get(String _id) {
		return areaDao.get(_id);
	}

	public List<Area> getAll() {
		return areaDao.getAll();
	}
	
}
