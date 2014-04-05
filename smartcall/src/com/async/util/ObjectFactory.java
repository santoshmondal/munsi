package com.async.util;

import com.smartcall.dao.impl.MongoCustomerDetailsDao;
import com.smartcall.service.CustomerDetailsService;


public class ObjectFactory {

	public static enum ObjectEnum {
		//@formatter:off
		// DAOs
		CUSTOMER_DETAILS_DAO(MongoCustomerDetailsDao.class.getName()),
		
		// Services
		CUSTOMER_DETAILS_SERVICE(CustomerDetailsService.class.getName());
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

	/**
	 * @param ObjectEnum
	 * @return
	 */
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

}
