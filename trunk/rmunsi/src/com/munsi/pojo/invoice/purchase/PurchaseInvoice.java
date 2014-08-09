package com.munsi.pojo.invoice.purchase;

import java.util.Set;

import com.munsi.pojo.invoice.BaseInvoice;
import com.munsi.pojo.master.Customer;

public class PurchaseInvoice extends BaseInvoice {

	private static final long serialVersionUID = 1L;

	private Set<PurchaseProduct> purchaseProductList;
	private Customer customer;

	public Set<PurchaseProduct> getPurchaseProductList() {
		return purchaseProductList;
	}

	public void setPurchaseProductList(Set<PurchaseProduct> purchaseProductList) {
		this.purchaseProductList = purchaseProductList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
