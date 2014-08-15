package com.munsi.pojo.invoice.sales;

import com.munsi.pojo.master.Product;

public class SalesProduct extends Product {

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

	/** quantity + freeQuintity */
	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	/** quantity + freeQuintity */
	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	/** MRP * quantity */
	public Double getDerTaxPrice() {
		return derTaxPrice;
	}

	/** MRP * quantity */
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

	/** ( derPrice + derTaxPrice ) - derDiscountPrice */
	public Double getNetPaybleProductPrice() {
		return netPaybleProductPrice;
	}

	/** ( derPrice + derTaxPrice ) - derDiscountPrice */
	public void setNetPaybleProductPrice(Double netPaybleProductPrice) {
		this.netPaybleProductPrice = netPaybleProductPrice;
	}

	public Double getRawDiscountPercent() {
		return rawDiscountPercent;
	}

	public void setRawDiscountPercent(Double rawDiscountPercent) {
		this.rawDiscountPercent = rawDiscountPercent;
	}

}
