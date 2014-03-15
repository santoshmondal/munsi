package com.munsi.dao;

import com.munsi.dao.impl.MongoCustomerDao;

public class DaoFactory {

	public static enum DaoEnum {
		CUSTOMER_DAO(MongoCustomerDao.class.getName());

		String className;

		DaoEnum(String className) {
			this.className = className;
		}

		@Override
		public String toString() {
			return className;
		}
	}

	public static BaseDao getDaoInstance(DaoEnum daoEnum) {
			try {
				Class<?> clazz = Class.forName(daoEnum.toString());
				return (BaseDao) clazz.newInstance();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		
		return null;
	}
	
	/*public static void main(String[] args) {
		BaseDao baseDao = DaoFactory.getDaoInstance(DaoEnum.CUSTOMER_DAO);
		CustomerDao customerDao = (CustomerDao) baseDao;
		System.out.println(customerDao);
		
	}*/
	
}
