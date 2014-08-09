package com.munsi.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.munsi.dao.PurchaseInvoiceDao;
import com.munsi.dao.impl.MongoPurchaseInvoiceDao;
import com.munsi.pojo.invoice.purchase.PurchaseInvoice;
import com.munsi.pojo.invoice.purchase.PurchaseProduct;
import com.munsi.pojo.master.Customer;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

public class PurchaseInvoiceServeice {

	private PurchaseInvoiceDao pInvoiceDao;

	public PurchaseInvoiceServeice() {
		Object object = ObjectFactory.getInstance(ObjectEnum.PURCHASE_INVOICE_DAO);
		if (object instanceof PurchaseInvoiceDao) {
			pInvoiceDao = (MongoPurchaseInvoiceDao) object;
		}
	}

	public Boolean create(PurchaseInvoice pInvoice) {
		return pInvoiceDao.create(pInvoice);
	}

	public Boolean update(PurchaseInvoice pInvoice) {
		return pInvoiceDao.update(pInvoice);
	}

	public Boolean delete(String _id) {
		return pInvoiceDao.delete(_id);
	}

	public PurchaseInvoice get(String _id) {
		return pInvoiceDao.get(_id);
	}

	public PurchaseInvoice get(String _id, Boolean withReferences) {
		return pInvoiceDao.get(_id, withReferences);
	}

	public List<PurchaseInvoice> getAll(Boolean withReferences) {
		return pInvoiceDao.getAll(withReferences);
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

		Customer sCustomer = new Customer();
		sCustomer.set_id("7");

		pInvoice.setCustomer(sCustomer);
		pInvoice.setPurchaseProductList(pProductList);

		return pInvoice;
	}
}
