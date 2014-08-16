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

	public Double getDerTaxPrice() {
		return derTaxPrice;
	}

	public void setDerTaxPrice(Double derTaxPrice) {
		this.derTaxPrice = derTaxPrice;
	}

	public Double getDerPrice() {
		return derPrice;
	}

	public void setDerPrice(Double derPrice) {
		this.derPrice = derPrice;
	}

	public Double getDerDiscountPrice() {
		return derDiscountPrice;
	}

	public void setDerDiscountPrice(Double derDiscountPrice) {
		this.derDiscountPrice = derDiscountPrice;
	}

	public Double getRawDiscountPercent() {
		return rawDiscountPercent;
	}

	public void setRawDiscountPercent(Double rawDiscountPercent) {
		this.rawDiscountPercent = rawDiscountPercent;
	}

	public Double getNetPaybleProductPrice() {
		return netPaybleProductPrice;
	}

	public void setNetPaybleProductPrice(Double netPaybleProductPrice) {
		this.netPaybleProductPrice = netPaybleProductPrice;
	}

}
