package com.munsi.util;

import java.util.HashMap;
import java.util.Map;

import com.munsi.dao.impl.MongoAccessUserDao;
import com.munsi.dao.impl.MongoAreaDao;
import com.munsi.dao.impl.MongoBeatDao;
import com.munsi.dao.impl.MongoCustomerDao;
import com.munsi.dao.impl.MongoMainAccountDao;
import com.munsi.dao.impl.MongoManufacturerDao;
import com.munsi.dao.impl.MongoOpeningProductStockDao;
import com.munsi.dao.impl.MongoProductDao;
import com.munsi.dao.impl.MongoProductGroupDao;
import com.munsi.dao.impl.MongoProductSchemeDao;
import com.munsi.dao.impl.MongoPurchaseInvoiceDao;
import com.munsi.dao.impl.MongoSalesInvoiceDao;
import com.munsi.dao.impl.MongoSupplierDao;
import com.munsi.dao.impl.MongoTaxDao;
import com.munsi.service.AccessUserServeice;
import com.munsi.service.AreaServeice;
import com.munsi.service.BeatServeice;
import com.munsi.service.CustomerServeice;
import com.munsi.service.MainAccountServeice;
import com.munsi.service.ManufacturerServeice;
import com.munsi.service.OpeningProductStockService;
import com.munsi.service.ProductGroupServeice;
import com.munsi.service.ProductSchemeService;
import com.munsi.service.ProductServeice;
import com.munsi.service.PurchaseInvoiceServeice;
import com.munsi.service.SalesInvoiceServeice;
import com.munsi.service.SalesPurchaseRuleManager;
import com.munsi.service.SupplierServeice;
import com.munsi.service.TaxServeice;

public class ObjectFactory {

	private static final Map<ObjectEnum, Object> objectFactoryMap = new HashMap<ObjectEnum, Object>();

	public static enum ObjectEnum {
		//@formatter:off
		// DAOs
		CUSTOMER_DAO(MongoCustomerDao.class.getName()), 
		AREA_DAO(MongoAreaDao.class.getName()),
		BEAT_DAO(MongoBeatDao.class.getName()),
		MAIN_ACCOUNT_DAO(MongoMainAccountDao.class.getName()),
		MANUFACTURER_DAO(MongoManufacturerDao.class.getName()),
		PRODUCT_GROUP_DAO(MongoProductGroupDao.class.getName()),
		TAX_DAO(MongoTaxDao.class.getName()),
		PRODUCT_DAO(MongoProductDao.class.getName()),
		ACCESS_USER_DAO(MongoAccessUserDao.class.getName()),
		SUPPLIER_DAO(MongoSupplierDao.class.getName()),
		OPENING_PRODUCT_STOCK_DAO(MongoOpeningProductStockDao.class.getName()),
		PRODUCT_SCHEME_DAO(MongoProductSchemeDao.class.getName()),
		SALES_INVOICE_DAO(MongoSalesInvoiceDao.class.getName()),
		PURCHASE_INVOICE_DAO(MongoPurchaseInvoiceDao.class.getName()),
		
		// Services
		AREA_SERVICE(AreaServeice.class.getName()),
		BEAT_SERVICE(BeatServeice.class.getName()),
		MANUFACTURER_SERVICE(ManufacturerServeice.class.getName()),
		CUSTOMER_SERVICE(CustomerServeice.class.getName()),
		PRODUCT_SERVICE(ProductServeice.class.getName()),
		PRODUCT_GROUP_SERVICE(ProductGroupServeice.class.getName()),
		ACCESS_USER_SERVICE(AccessUserServeice.class.getName()),
		SUPPLIER_SERVICE(SupplierServeice.class.getName()),
		TAX_SERVICE(TaxServeice.class.getName()),
		MAIN_ACCOUNT_SERVICE(MainAccountServeice.class.getName()),
		OPENING_PRODUCT_STOCK_SERVICE(OpeningProductStockService.class.getName()),
		PRODUCT_SCHEME_SERVICE(ProductSchemeService.class.getName()),	
		SALES_INVOICE_SERVICE(SalesInvoiceServeice.class.getName()),
		PURCHASE_INVOICE_SERVICE(PurchaseInvoiceServeice.class.getName()),
		
		SALES_PURCHASE_RULE(SalesPurchaseRuleManager.class.getName());
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

	public static Object getInstance(ObjectEnum objectEnum) {
		Object instance = null;
		try {
			instance = objectFactoryMap.get(objectEnum);
			if (instance == null) {
				Class<?> clazz = Class.forName(objectEnum.toString());
				instance = clazz.newInstance();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return instance;
	}

	/*
	 * public static void main(String[] args) { BaseDao baseDao =
	 * DaoFactory.getDaoInstance(DaoEnum.CUSTOMER_DAO); CustomerDao customerDao
	 * = (CustomerDao) baseDao; System.out.println(customerDao);
	 * 
	 * }
	 */

}
