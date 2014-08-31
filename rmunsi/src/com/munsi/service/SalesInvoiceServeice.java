package com.munsi.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.munsi.dao.SalesInvoiceDao;
import com.munsi.pojo.invoice.sales.SalesInvoice;
import com.munsi.pojo.invoice.sales.SalesProduct;
import com.munsi.pojo.master.Customer;
import com.munsi.util.CommonUtil;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

public class SalesInvoiceServeice {

	private SalesInvoiceDao sInvoiceDao;
	private CustomerServeice customerService;
	private SalesPurchaseRuleManager ruleManager;

	public SalesInvoiceServeice() {
		Object object = ObjectFactory.getInstance(ObjectEnum.SALES_INVOICE_DAO);
		if (object instanceof SalesInvoiceDao) {
			sInvoiceDao = (SalesInvoiceDao) object;
		}

		customerService = (CustomerServeice) ObjectFactory.getInstance(ObjectEnum.CUSTOMER_SERVICE);

		ruleManager = (SalesPurchaseRuleManager) ObjectFactory.getInstance(ObjectEnum.SALES_PURCHASE_RULE);
	}

	public Boolean create(SalesInvoice sInvoice) throws Exception {
		// Rules for calculating TAXs, Discount, Bill Amount etc.
		ruleManager.applySalesInvoiceRule(sInvoice);

		// Updating Customer Object [Outstanding Amount]
		ruleManager.applyCustomerUpdates(sInvoice);
		synchronized (sInvoice) {
			StringBuffer errorStringBuffer = new StringBuffer();
			boolean bool = ruleManager.validateStockQuantity(sInvoice.getSalesProductList(), errorStringBuffer);

			if (!bool) {
				throw new Exception(errorStringBuffer.toString());
			}
			// update inventory
			ruleManager.applyInventoryUpdatesSales(sInvoice);

			// Object update and creation
			customerService.update(sInvoice.getCustomer());
		}
		return sInvoiceDao.create(sInvoice);
	}

	public Boolean update(SalesInvoice sInvoice) throws Exception {
		// Rules for calculating TAXs, Discount, Bill Amount etc.
		ruleManager.applySalesInvoiceRule(sInvoice);

		// Updating Customer Object [Outstanding Amount]
		ruleManager.applyCustomerUpdates(sInvoice);
		synchronized (sInvoice) {
			StringBuffer errorStringBuffer = new StringBuffer();
			boolean bool = ruleManager.validateStockQuantity(sInvoice.getSalesProductList(), errorStringBuffer);

			if (!bool) {
				throw new Exception(errorStringBuffer.toString());
			}
			// update inventory
			ruleManager.applyInventoryUpdatesSales(sInvoice);

			// Object update and creation
			customerService.update(sInvoice.getCustomer());
		}
		return sInvoiceDao.update(sInvoice);
	}

	public Boolean delete(String _id) {
		return sInvoiceDao.delete(_id);
	}

	public SalesInvoice get(String _id) {
		return sInvoiceDao.get(_id);
	}

	public SalesInvoice get(String _id, Boolean withReferences) {
		return sInvoiceDao.get(_id, withReferences);
	}

	public List<SalesInvoice> getAll(Boolean withReferences) {
		List<SalesInvoice> sInvoiceList = sInvoiceDao.getAll(withReferences);
		for (SalesInvoice invoice : sInvoiceList) {
			invoice.setSctime(CommonUtil.longToStringDate(invoice.getCtime().getTime()));
			invoice.setSutime(CommonUtil.longToStringDate(invoice.getUtime().getTime()));
			//invoice.setSinvoiceDate(CommonUtil.longToStringDate(invoice.getInvoiceDate().getTime()));
		}

		return sInvoiceList;
	}

	public static void main(String[] args) {
		SalesInvoiceServeice ref = (SalesInvoiceServeice) ObjectFactory.getInstance(ObjectEnum.SALES_INVOICE_SERVICE);

		// create test
		SalesInvoice sInvoice = testSalesInvoice();
		//ref.create(sInvoice);

		// get test
		/*SalesInvoice sInvoice = ref.get("1", true);
		System.out.println(sInvoice);*/

		// get all test
		List<SalesInvoice> all = ref.getAll(true);
		System.out.println(all);

		// delete test
		ref.delete("2");

		System.out.println("DONE");
	}

	private static SalesInvoice testSalesInvoice() {
		SalesInvoice sInvoice = new SalesInvoice();

		Set<SalesProduct> sProductList = new LinkedHashSet<SalesProduct>();
		for (int i = 0; i < 5; i++) {
			SalesProduct sProduct = new SalesProduct();
			sProduct.set_id("id_" + i);
			sProduct.setName("name_" + i);
			sProduct.setCode("code_" + i);
			sProduct.setBarCode("barcode_" + i);

			sProductList.add(sProduct);
		}

		Customer sCustomer = new Customer();
		sCustomer.set_id("7");

		sInvoice.setCustomer(sCustomer);
		sInvoice.setSalesProductList(sProductList);

		return sInvoice;
	}

}
