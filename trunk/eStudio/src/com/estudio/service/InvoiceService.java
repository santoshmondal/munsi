package com.estudio.service;

import java.util.List;

import com.async.util.ObjectFactory;
import com.async.util.ObjectFactory.ObjectEnum;
import com.estudio.dao.InvoiceDao;
import com.estudio.pojo.Invoice;

public class InvoiceService {
	private InvoiceDao invoiceDao;
	
	public InvoiceService(){
		invoiceDao = (InvoiceDao) ObjectFactory.getInstance(ObjectEnum.INVOICE_DAO);
	}
	
	public Boolean create(Invoice invoice){
		return invoiceDao.create(invoice);
	}
	
	public Boolean update(Invoice invoice){
		return invoiceDao.update(invoice);
	}
	
	public List<Invoice> getAll(){
		return invoiceDao.getAll();
	}
	
	public Invoice get( String _id ){
		return invoiceDao.get(_id);
	}
	
	public Boolean delete(String _id){
		return invoiceDao.delete(_id);
	}
	
	public Boolean updateStatus(String _id, String status){
		return invoiceDao.updateStatus(_id, status);
	}
	
}
