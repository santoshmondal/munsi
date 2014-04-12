package com.async.util;

import com.smartcall.dao.impl.MongoAccessUserDao;
import com.smartcall.dao.impl.MongoAppointmentDao;
import com.smartcall.dao.impl.MongoCustomerDetailsDao;
import com.smartcall.service.AppointmentService;
import com.smartcall.service.CustomerDetailsService;


public class ObjectFactory {

	public static enum ObjectEnum {
		//@formatter:off
		// DAOs
		CUSTOMER_DETAILS_DAO(MongoCustomerDetailsDao.class.getName()),
		ACCESS_USER_DAO(MongoAccessUserDao.class.getName()),
		APPOINTMENT_DAO(MongoAppointmentDao.class.getName()),
		// Services
		CUSTOMER_DETAILS_SERVICE(CustomerDetailsService.class.getName()),
		APPOINTMENT_SERVICE(AppointmentService.class.getName());
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
