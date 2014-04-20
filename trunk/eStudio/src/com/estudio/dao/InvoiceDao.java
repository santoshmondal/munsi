package com.estudio.dao;

import java.util.List;

import com.estudio.pojo.Invoice;

public interface InvoiceDao {

	public Invoice create(Invoice invoice);

	public Boolean update(Invoice invoice);

	public Boolean delete(String _id);

	public Invoice get(String _id);

	public List<Invoice> getAll();

	public Boolean updateStatus(String _id, String status);

}
