package com.munsi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.munsi.dao.SalesInvoiceDao;
import com.munsi.pojo.invoice.sales.SalesInvoice;

public class MongoSalesInvoiceDao implements SalesInvoiceDao {
	private static final Logger LOG = Logger.getLogger(MongoSalesInvoiceDao.class);

	@Override
	public Boolean create(SalesInvoice sInvoice) {
		Boolean sReturn = false;
		try {

		} catch (Exception e) {
			LOG.error(e);
		}

		return sReturn;
	}

	@Override
	public Boolean update(SalesInvoice sInvoice) {
		Boolean sReturn = false;
		try {

		} catch (Exception e) {
			LOG.error(e);
		}

		return sReturn;
	}

	@Override
	public Boolean delete(String _id) {
		Boolean sReturn = false;
		try {

		} catch (Exception e) {
			LOG.error(e);
		}

		return sReturn;
	}

	@Override
	public SalesInvoice get(String _id) {
		SalesInvoice sInvoice = null;
		try {

		} catch (Exception e) {
			LOG.error(e);
		}

		return sInvoice;
	}

	@Override
	public SalesInvoice get(String _id, Boolean withReferences) {
		SalesInvoice sInvoice = null;
		try {

		} catch (Exception e) {
			LOG.error(e);
		}

		return sInvoice;
	}

	@Override
	public List<SalesInvoice> getAll() {
		List<SalesInvoice> sInvoiceList = new ArrayList<SalesInvoice>();
		try {

		} catch (Exception e) {
			LOG.error(e);
		}

		return sInvoiceList;
	}

}
