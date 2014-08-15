package com.munsi.pojo.invoice.purchase;

import java.util.Set;

import com.munsi.pojo.invoice.BaseInvoice;
import com.munsi.pojo.master.Supplier;

public class PurchaseInvoice extends BaseInvoice {

	private static final long serialVersionUID = 1L;

	private Set<PurchaseProduct> purchaseProductList;
	private Supplier supplier;

	public Set<PurchaseProduct> getPurchaseProductList() {
		return purchaseProductList;
	}

	public void setPurchaseProductList(Set<PurchaseProduct> purchaseProductList) {
		this.purchaseProductList = purchaseProductList;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
