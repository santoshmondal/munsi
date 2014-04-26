package com.estudio.service;

import java.util.List;

import com.async.util.ObjectFactory;
import com.async.util.ObjectFactory.ObjectEnum;
import com.estudio.dao.MasterDao;
import com.estudio.pojo.master.Master;

public class MasterService {
	private MasterDao masterDao;
	
	public MasterService(){
		masterDao = (MasterDao) ObjectFactory.getInstance(ObjectEnum.MASTER_DAO);
	}
	
	public Boolean create(Master master){
		return masterDao.create(master);
	}
	
	public Boolean update(Master master){
		return masterDao.update(master);
	}
	
	public List<Master> getAll(){
		return masterDao.getAll();
	}
	
	public Master get( String _id ){
		return masterDao.get(_id);
	}
	
	public Boolean delete(String _id){
		return masterDao.delete(_id);
	}
	
}
