package com.munsi.util;

import com.munsi.dao.impl.MongoAccessUserDao;
import com.munsi.dao.impl.MongoAreaDao;
import com.munsi.dao.impl.MongoCustomerDao;
import com.munsi.dao.impl.MongoMainAccountDao;
import com.munsi.dao.impl.MongoManufacturerDao;
import com.munsi.dao.impl.MongoProductDao;
import com.munsi.dao.impl.MongoProductGroupDao;
import com.munsi.dao.impl.MongoSupplierDao;
import com.munsi.dao.impl.MongoTaxDao;
import com.munsi.service.CustomerServeice;
import com.munsi.service.MainAccountServeice;

public class ObjectFactory {

	public static enum ObjectEnum {
		//@formatter:off
		// DAOs
		CUSTOMER_DAO(MongoCustomerDao.class.getName()), 
		AREA_DAO(MongoAreaDao.class.getName()),
		MAIN_ACCOUNT_DAO(MongoMainAccountDao.class.getName()),
		MANUFACTURER_DAO(MongoManufacturerDao.class.getName()),
		PRODUCT_GROUP_DAO(MongoProductGroupDao.class.getName()),
		TAX_DAO(MongoTaxDao.class.getName()),
		PRODUCT_DAO(MongoProductDao.class.getName()),
		ACCESS_USER_DAO(MongoAccessUserDao.class.getName()),
		SUPPLIER_DAO(MongoSupplierDao.class.getName()),
		
		
		// Services
		CUSTOMER_SERVICE(CustomerServeice.class.getName()),
		MAIN_ACCOUNT_SERVICE(MainAccountServeice.class.getName());		
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