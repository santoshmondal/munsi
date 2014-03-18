package com.munsi.dao;

import com.munsi.dao.impl.MongoAreaDao;
import com.munsi.dao.impl.MongoCustomerDao;
import com.munsi.dao.impl.MongoMainAccountDao;
import com.munsi.dao.impl.MongoManufacturerDao;
import com.munsi.service.CustomerServeice;

public class ObjectFactory {

	public static enum ObjectEnum {
		//@formatter:off
		// DAOs
		CUSTOMER_DAO(MongoCustomerDao.class.getName()), 
		AREA_DAO(MongoAreaDao.class.getName()),
		MAIN_AC_DAO(MongoMainAccountDao.class.getName()),
		MANUFACTURER_DAO(MongoManufacturerDao.class.getName()),
		
		
		// Services
		CUSTOMER_SERVICE(CustomerServeice.class.getName());
		
		//@formatter:on

		private final String className;

		ObjectEnum(String className) {
			this.className = className;
		}

		@Override
		public String toString() {
			return className;
		}
	}

	public static Object getDaoInstance(ObjectEnum ObjectEnum) {
		try {
			Class<?> clazz = Class.forName(ObjectEnum.toString());
			return clazz.newInstance();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * public static void main(String[] args) { BaseDao baseDao =
	 * DaoFactory.getDaoInstance(DaoEnum.CUSTOMER_DAO); CustomerDao customerDao
	 * = (CustomerDao) baseDao; System.out.println(customerDao);
	 * 
	 * }
	 */

}
