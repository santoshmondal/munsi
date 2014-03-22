package com.munsi.service;

import java.util.List;

import com.munsi.dao.MainAccountDao;
import com.munsi.dao.ObjectFactory;
import com.munsi.dao.ObjectFactory.ObjectEnum;
import com.munsi.pojo.master.MainAccount;

public class MainAccountServeice {

	private MainAccountDao mainAccountDao;

	public MainAccountServeice() {
		Object object = ObjectFactory.getDaoInstance(ObjectEnum.MAIN_AC_DAO);
		if (object instanceof MainAccountDao) {
			mainAccountDao = (MainAccountDao) object;
		}

	}
	
	public Boolean create(MainAccount mainAccount) {
		return mainAccountDao.create(mainAccount);
	}

	public Boolean update(MainAccount mainAccount) {
		return mainAccountDao.update(mainAccount);
	}

	public Boolean delete(String _id) {
		return mainAccountDao.delete(_id);
	}

	public MainAccount get(String _id) {
		return mainAccountDao.get(_id);
	}

	public List<MainAccount> getAll() {
		return mainAccountDao.getAll();
	}
}
