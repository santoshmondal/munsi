package com.munsi.dao;

import java.util.List;

import com.munsi.pojo.invoice.sales.SalesInvoice;

public interface SalesInvoiceDao {

	public Boolean create(SalesInvoice sInvoice);

	public Boolean update(SalesInvoice sInvoice);

	public Boolean delete(String _id);

	public SalesInvoice get(String _id);

	public SalesInvoice get(String _id, Boolean withReferences);

	public List<SalesInvoice> getAll();

}
