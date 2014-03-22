package com.munsi.service;

import java.util.List;

import com.munsi.dao.BeatDao;
import com.munsi.dao.impl.MongoBeatDao;
import com.munsi.pojo.master.Beat;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

public class BeatServeice {

	private BeatDao beatDao;

	public BeatServeice() {
		Object object = ObjectFactory.getDaoInstance(ObjectEnum.BEAT_DAO);
		if (object instanceof BeatDao) {
			beatDao = (MongoBeatDao) object;
		}

	}
	
	public Boolean create(Beat beat) {
		return beatDao.create(beat);
	}

	public Boolean update(Beat beat) {
		return beatDao.update(beat);
	}

	public Boolean delete(String _id) {
		return beatDao.delete(_id);
	}

	public Beat get(String _id) {
		return beatDao.get(_id);
	}

	public Beat get(String _id, Boolean withReferences){
		return beatDao.get(_id, withReferences);
	}
	
	/**
	 * Get list of Beat instance, Beat instance does not load references
	 * instance,
	 * 
	 * @return ArraList of Beat
	 */
	public List<Beat> getAll() {
		return beatDao.getAll();
	}
	
	
	public List<Beat> getAll(Boolean withReferences) {
		return beatDao.getAll(withReferences);
	}
	
	
	public List<Beat> getBeatListByArea(String area_id){
		return beatDao.getBeatListByArea(area_id);
	}

	public List<Beat> getBeatListByArea(String area_id,Boolean withReferences){
		return beatDao.getBeatListByArea(area_id,withReferences);
	}
}
