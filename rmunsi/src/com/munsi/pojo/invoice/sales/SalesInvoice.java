package com.munsi.pojo.invoice.sales;

import java.util.Set;

import com.munsi.pojo.invoice.BaseInvoice;
import com.munsi.pojo.master.Customer;

public class SalesInvoice extends BaseInvoice {

	private static final long serialVersionUID = 1L;

	private Set<SalesProduct> salesProductList;
	private Customer customer;

	public Set<SalesProduct> getSalesProductList() {
		return salesProductList;
	}

	public void setSalesProductList(Set<SalesProduct> salesProductList) {
		this.salesProductList = salesProductList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
