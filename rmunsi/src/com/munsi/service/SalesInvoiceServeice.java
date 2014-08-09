package com.munsi.service;

import java.util.List;

import com.munsi.dao.ProductDao;
import com.munsi.dao.SalesInvoiceDao;
import com.munsi.pojo.invoice.sales.SalesInvoice;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

public class SalesInvoiceServeice {

	private SalesInvoiceDao sInvoiceDao;

	public SalesInvoiceServeice() {
		Object object = ObjectFactory.getInstance(ObjectEnum.SALES_INVOICE_DAO);
		if (object instanceof ProductDao) {
			sInvoiceDao = (SalesInvoiceDao) object;
		}
	}

	public Boolean create(SalesInvoice sInvoice) {
		return sInvoiceDao.create(sInvoice);
	}

	public Boolean update(SalesInvoice sInvoice) {
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
		return sInvoiceDao.getAll(withReferences);
	}

	public static void main(String[] args) {
		SalesInvoiceServeice ref = (SalesInvoiceServeice) ObjectFactory.getInstance(ObjectEnum.SALES_INVOICE_SERVICE);
		System.out.println(ref);
	}
}
