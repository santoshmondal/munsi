package com.estudio.service;

import java.util.List;
import java.util.Map;

import com.async.util.CommonUtil;
import com.async.util.ObjectFactory;
import com.async.util.ObjectFactory.ObjectEnum;
import com.estudio.dao.CustomerDao;
import com.estudio.dao.InvoiceDao;
import com.estudio.pojo.Customer;
import com.estudio.pojo.Invoice;

public class InvoiceService {
	private InvoiceDao invoiceDao;
	private CustomerDao customerDao;

	public InvoiceService() {
		invoiceDao = (InvoiceDao) ObjectFactory.getInstance(ObjectEnum.INVOICE_DAO);
		customerDao = (CustomerDao) ObjectFactory.getInstance(ObjectEnum.CUSTOMER_DAO);
	}

	public Invoice create(Invoice invoice) {
		upsertCustomer(invoice.getCustomer());
		return invoiceDao.create(invoice);
	}

	public Boolean update(Invoice invoice) {
		if(invoice.getCustomer() != null)
			upsertCustomer(invoice.getCustomer());
		return invoiceDao.update(invoice);
	}

	public List<Invoice> getAll() {
		return invoiceDao.getAll();
	}

	public List<Invoice> getAllByField(Map<String, String> map) {
		return invoiceDao.getAllByField(map);
	}

	public Invoice get(String _id) {
		return invoiceDao.get(_id);
	}

	public Boolean delete(String _id) {
		return invoiceDao.delete(_id);
	}

	public Boolean updateStatus(String _id, String status) {
		return invoiceDao.updateStatus(_id, status);
	}
	
	public Boolean updateBalance(String _id, String status) {
		return invoiceDao.updateStatus(_id, status);
	}

	public Boolean upsertCustomer(Customer customer) {
		String customerId = customer.get_id();
		Customer dbCustomer = customerDao.get(customerId);
		if (dbCustomer == null) {
			customerDao.create(customer);
		} else {
			customerDao.update(customer);
		}

		return true;
	}

	public static void main(String[] args) {
		InvoiceService iSerice = (InvoiceService) ObjectFactory.getInstance(ObjectEnum.INVOICE_SERVICE);

		List<Invoice> all = iSerice.getAll();

		for (Invoice invRef : all) {
			invRef.setsCtime(CommonUtil.longToStringDate(invRef.getCtime().getTime()));
			invRef.setsUtime(CommonUtil.longToStringDate(invRef.getUtime().getTime()));

			Customer sCustomer = invRef.getCustomer();
			if (sCustomer != null) {

				if (sCustomer.getDob() != null) {
					sCustomer.setsDob(CommonUtil.longToStringDate(sCustomer.getDob().getTime()));
				}
				if (sCustomer.getMarriageDate() != null) {
					sCustomer.setsMarriageDate(CommonUtil.longToStringDate(sCustomer.getMarriageDate().getTime()));
				}
			}
		}

		System.out.println(all);

	}
}
