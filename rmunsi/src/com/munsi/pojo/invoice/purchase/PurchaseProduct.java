package com.munsi.pojo.invoice.purchase;

import com.munsi.pojo.master.Product;

public class PurchaseProduct extends Product {

	private static final long serialVersionUID = 1L;

	private Integer quantity;
	private Integer freeQuantity;
	private Integer totalQuantity; // quantity + freeQuintity

	private Double derTaxPrice;
	private Double derPrice; // Sale Price * quantity
	private Double derDiscountPrice;

	private Double rawDiscountPercent;

	// ( derPrice + derTaxPrice ) - derDiscountPrice //
	private Double netPaybleProductPrice;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getFreeQuantity() {
		return freeQuantity;
	}

	public void setFreeQuantity(Integer freeQuantity) {
		this.freeQuantity = freeQuantity;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

}