package com.munsi.service;

import java.util.List;

import com.munsi.dao.PurchaseInvoiceDao;
import com.munsi.dao.impl.MongoPurchaseInvoiceDao;
import com.munsi.pojo.invoice.purchase.PurchaseInvoice;
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
		System.out.println(ref);
	}

}
