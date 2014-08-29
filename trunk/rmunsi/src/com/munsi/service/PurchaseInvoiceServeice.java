package com.munsi.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.munsi.dao.PurchaseInvoiceDao;
import com.munsi.dao.impl.MongoPurchaseInvoiceDao;
import com.munsi.pojo.invoice.purchase.PurchaseInvoice;
import com.munsi.pojo.invoice.purchase.PurchaseProduct;
import com.munsi.pojo.master.Supplier;
import com.munsi.util.CommonUtil;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

public class PurchaseInvoiceServeice {

	private PurchaseInvoiceDao pInvoiceDao;
	private SalesPurchaseRuleManager ruleManager;

	private SupplierServeice supplierService;

	public PurchaseInvoiceServeice() {
		Object object = ObjectFactory.getInstance(ObjectEnum.PURCHASE_INVOICE_DAO);
		if (object instanceof PurchaseInvoiceDao) {
			pInvoiceDao = (MongoPurchaseInvoiceDao) object;
		}

		ruleManager = (SalesPurchaseRuleManager) ObjectFactory.getInstance(ObjectEnum.SALES_PURCHASE_RULE);

		supplierService = (SupplierServeice) ObjectFactory.getInstance(ObjectEnum.SUPPLIER_SERVICE);
	}

	public Boolean create(PurchaseInvoice pInvoice) {
		// Rules for calculating TAXs, Discount, Bill Amount etc.
		ruleManager.applyPurchaseInvoiceRule(pInvoice);

		// Updating Supplier Object [Outstanding Amount]
		ruleManager.applySupplierUpdates(pInvoice);

		// update inventory
		ruleManager.applyInventoryUpdatesPurchase(pInvoice);

		// Object update and creation
		supplierService.update(pInvoice.getSupplier());

		return pInvoiceDao.create(pInvoice);
	}

	public Boolean update(PurchaseInvoice pInvoice) {
		// Rules for calculating TAXs, Discount, Bill Amount etc.
		ruleManager.applyPurchaseInvoiceRule(pInvoice);

		// Updating Supplier Object [Outstanding Amount]
		ruleManager.applySupplierUpdates(pInvoice);

		// update inventory
		ruleManager.applyInventoryUpdatesPurchase(pInvoice);

		// Object update and creation
		supplierService.update(pInvoice.getSupplier());

		return pInvoiceDao.update(pInvoice);
	}

	public Boolean delete(String _id) {
		return pInvoiceDao.delete(_id);
	}

	public PurchaseInvoice get(String _id) {

		PurchaseInvoice pInvoice = pInvoiceDao.get(_id);
		pInvoice.setSctime(CommonUtil.longToStringDate(pInvoice.getCtime().getTime()));
		pInvoice.setSutime(CommonUtil.longToStringDate(pInvoice.getUtime().getTime()));

		return pInvoice;
	}

	public PurchaseInvoice get(String _id, Boolean withReferences) {
		PurchaseInvoice pInvoice = pInvoiceDao.get(_id, withReferences);

		pInvoice.setSctime(CommonUtil.longToStringDate(pInvoice.getCtime().getTime()));
		pInvoice.setSutime(CommonUtil.longToStringDate(pInvoice.getUtime().getTime()));

		return pInvoice;
	}

	public List<PurchaseInvoice> getAll(Boolean withReferences) {
		List<PurchaseInvoice> pInvoiceList = pInvoiceDao.getAll(withReferences);
		for (PurchaseInvoice pInvoice : pInvoiceList) {
			pInvoice.setSctime(CommonUtil.longToStringDate(pInvoice.getCtime().getTime()));
			pInvoice.setSutime(CommonUtil.longToStringDate(pInvoice.getUtime().getTime()));
			pInvoice.setSinvoiceDate(CommonUtil.longToStringDate(pInvoice.getInvoiceDate().getTime()));
		}

		return pInvoiceList;
	}

	public static void main(String[] args) {
		PurchaseInvoiceServeice ref = (PurchaseInvoiceServeice) ObjectFactory.getInstance(ObjectEnum.PURCHASE_INVOICE_SERVICE);
		// create test
		PurchaseInvoice pInvoice = testPurchaseInvoice();
		ref.create(pInvoice);

		// get test
		/*PurchaseInvoice pInvoice = ref.get("1", true);
		System.out.println(pInvoice);*/

		// get all test
		List<PurchaseInvoice> all = ref.getAll(true);
		System.out.println(all);

		// delete test
		// ref.delete("2");

		System.out.println("DONE");
	}

	private static PurchaseInvoice testPurchaseInvoice() {
		PurchaseInvoice pInvoice = new PurchaseInvoice();

		Set<PurchaseProduct> pProductList = new LinkedHashSet<PurchaseProduct>();
		for (int i = 0; i < 5; i++) {
			PurchaseProduct pProduct = new PurchaseProduct();
			pProduct.set_id("id_" + i);
			pProduct.setName("name_" + i);
			pProduct.setCode("code_" + i);
			pProduct.setBarCode("barcode_" + i);

			pProductList.add(pProduct);
		}

		Supplier sSupplier = new Supplier();
		sSupplier.set_id("7");

		pInvoice.setSupplier(sSupplier);
		pInvoice.setPurchaseProductList(pProductList);

		return pInvoice;
	}
}
