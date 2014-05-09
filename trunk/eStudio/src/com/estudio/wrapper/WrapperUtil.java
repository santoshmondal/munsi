package com.estudio.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.estudio.pojo.Customer;
import com.estudio.pojo.Invoice;

public class WrapperUtil {

	public static List<InvoiceFlat> getInvoiceFlatList(List<Invoice> invoiceList) {
		List<InvoiceFlat> invoiceFlatList = new ArrayList<InvoiceFlat>();

		InvoiceFlat invoiceFlat = null;
		for (Invoice invoice : invoiceList) {
			invoiceFlat = new InvoiceFlat();

			invoiceFlat.setInvoiceNumber(invoice.getInvoiceNumber());
			invoiceFlat.setInvoiceDate(invoice.getInvoiceDate());
			invoiceFlat.setInvoiceTotalAmount(invoice.getTotalAmount());
			invoiceFlat.setInvoiceAdvanceBal(invoice.getAdvanceBal());
			invoiceFlat.setInvoiceRemainingBal(invoice.getRemainingBal());
			invoiceFlat.setInvoiceDelivaryDate(invoice.getDelivaryDate());
			invoiceFlat.setInvoiceStatus(invoice.getStatus());

			Customer customer = invoice.getCustomer();
			if (customer != null) {
				invoiceFlat.setCustomerName(customer.getName());
				invoiceFlat.setCustomerAddress(customer.getAddress());
				invoiceFlat.setCustomerMarriageDate(customer.getMarriageDate());
				invoiceFlat.setCustomerDOB(customer.getDob());
				invoiceFlat.setCustomerEmailId(customer.getEmailId());
			}
		}

		return invoiceFlatList;
	}
}
