package com.munsi.dao;

import java.util.List;

import com.munsi.pojo.invoice.purchase.PurchaseInvoice;

public interface PurchaseInvoiceDao {

	public Boolean create(PurchaseInvoice pInvoice);

	public Boolean update(PurchaseInvoice pInvoice);

	public Boolean delete(String _id);

	public PurchaseInvoice get(String _id);

	public PurchaseInvoice get(String _id, Boolean withReferences);

	public List<PurchaseInvoice> getAll(Boolean withReferences);

}
