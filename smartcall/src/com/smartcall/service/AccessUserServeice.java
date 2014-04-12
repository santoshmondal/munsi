package com.smartcall.service;

import java.util.List;

import com.async.util.ObjectFactory;
import com.async.util.ObjectFactory.ObjectEnum;
import com.smartcall.dao.AccessUserDao;
import com.smartcall.pojo.AccessUser;

public class AccessUserServeice {

	private AccessUserDao accessUserDao;

	public AccessUserServeice() {
		Object object = ObjectFactory.getInstance(ObjectEnum.ACCESS_USER_DAO);
		if (object instanceof AccessUserDao) {
			accessUserDao = (AccessUserDao) object;
		}

	}
	
	public Boolean create(AccessUser accessUser) {
		return accessUserDao.create(accessUser);
	}

	public Boolean update(AccessUser accessUser) {
		return accessUserDao.update(accessUser);
	}

	public Boolean delete(String _id) {
		return accessUserDao.delete(_id);
	}

	public AccessUser get(String _id) {
		return accessUserDao.get(_id);
	}

	
	public List<AccessUser> getAll() {
		return accessUserDao.getAll();
	}
	
	public AccessUser authenticate(String userName, String password){
		return accessUserDao.authenticate(userName, password);
	}

}
