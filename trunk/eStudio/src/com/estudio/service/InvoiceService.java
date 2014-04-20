package com.estudio.service;

import java.util.List;

import com.async.util.ObjectFactory;
import com.async.util.ObjectFactory.ObjectEnum;
import com.estudio.dao.InvoiceDao;
import com.estudio.pojo.Customer;
import com.estudio.pojo.Invoice;

public class InvoiceService {
	private InvoiceDao invoiceDao;
	
	public InvoiceService(){
		invoiceDao = (InvoiceDao) ObjectFactory.getInstance(ObjectEnum.INVOICE_DAO);
	}
	
	public Invoice create(Invoice invoice){
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
	
	
	public static void main(String[] args) {
		InvoiceService iSerice = (InvoiceService)ObjectFactory.getInstance(ObjectEnum.INVOICE_SERVICE);
		
		Customer customer =  new Customer();
		customer.set_id("1");
		
		Invoice invoice = new Invoice();
		invoice.setStatus("ENUM_STATUS");
		invoice.setCustomer(customer);
		
		
		iSerice.create(invoice);
		
	}
}
