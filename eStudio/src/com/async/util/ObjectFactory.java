package com.async.util;

import com.estudio.dao.impl.MongoAccessUserDao;
import com.estudio.dao.impl.MongoCustomerDao;
import com.estudio.dao.impl.MongoInvoiceDao;
import com.estudio.dao.impl.MongoMasterDao;
import com.estudio.service.AccessUserServeice;
import com.estudio.service.CustomerService;
import com.estudio.service.InvoiceService;

public class ObjectFactory {

	public static enum ObjectEnum {
		//@formatter:off
		// DAOs
		CUSTOMER_DAO(MongoCustomerDao.class.getName()),
		ACCESS_USER_DAO(MongoAccessUserDao.class.getName()),
		MASTER_DAO(MongoMasterDao.class.getName()),
		INVOICE_DAO(MongoInvoiceDao.class.getName()),
		// Services
		CUSTOMER_SERVICE(CustomerService.class.getName()),
		ACCESS_USER_SERVICE(AccessUserServeice.class.getName()),
		INVOICE_SERVICE(InvoiceService.class.getName());
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

	public static Object getInstance(ObjectEnum ObjectEnum) {
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
