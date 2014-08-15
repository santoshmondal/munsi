package com.munsi.pojo.invoice;

import java.io.Serializable;

import com.munsi.pojo.BasePojo;

public class BaseInvoice extends BasePojo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String _id;
	private String invoiceNumber;
	private Double invoiceDiscountPrice;
	private Double invoiceTaxPercent;

	private Double sumOfNetPaybleProductPrice;

	// sumOfNetPaybleProductPrice + invoiceTaxPrice - invoiceDiscountPrice
	private Double netPayblePrice;

	private Integer numberOfItem;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Double getInvoiceDiscountPrice() {
		return invoiceDiscountPrice;
	}

	public void setInvoiceDiscountPrice(Double invoiceDiscountPrice) {
		this.invoiceDiscountPrice = invoiceDiscountPrice;
	}

	public Double getInvoiceTaxPercent() {
		return invoiceTaxPercent;
	}

	public void setInvoiceTaxPercent(Double invoiceTaxPercent) {
		this.invoiceTaxPercent = invoiceTaxPercent;
	}

	/** sumOfNetPaybleProductPrice + invoiceTaxPrice - invoiceDiscountPrice */
	public Double getSumOfNetPaybleProductPrice() {
		return sumOfNetPaybleProductPrice;
	}

	/** sumOfNetPaybleProductPrice + invoiceTaxPrice - invoiceDiscountPrice */
	public void setSumOfNetPaybleProductPrice(Double sumOfNetPaybleProductPrice) {
		this.sumOfNetPaybleProductPrice = sumOfNetPaybleProductPrice;
	}

	public Double getNetPayblePrice() {
		return netPayblePrice;
	}

	public void setNetPayblePrice(Double netPayblePrice) {
		this.netPayblePrice = netPayblePrice;
	}

	public Integer getNumberOfItem() {
		return numberOfItem;
	}

	public void setNumberOfItem(Integer numberOfItem) {
		this.numberOfItem = numberOfItem;
	}

}
