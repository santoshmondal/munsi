package com.munsi.pojo.invoice;

import java.io.Serializable;
import java.util.Date;

import com.munsi.pojo.BasePojo;

public class BaseInvoice extends BasePojo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String _id;
	private String invoiceNumber;

	private Date invoiceDate;
	private String sinvoiceDate;

	private Double invoiceDiscountPrice;
	private Double invoiceDiscountPercent;

	private Double invoiceTaxPercent;
	private Double invoiceTaxPrice;

	private Double sumOfNetPaybleProductPrice;

	// sumOfNetPaybleProductPrice + invoiceTaxPrice - invoiceDiscountPrice
	private Double netPayblePrice;
	private Integer numberOfItem;
	private Double paidAmount;
	private Double freight;
	private Double roundOfAmount;

	private Payment payment;

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

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
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

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getRoundOfAmount() {
		return roundOfAmount;
	}

	public void setRoundOfAmount(Double roundOfAmount) {
		this.roundOfAmount = roundOfAmount;
	}

	public Double getInvoiceDiscountPercent() {
		return invoiceDiscountPercent;
	}

	public void setInvoiceDiscountPercent(Double invoiceDiscountPercent) {
		this.invoiceDiscountPercent = invoiceDiscountPercent;
	}

	public Double getInvoiceTaxPrice() {
		return invoiceTaxPrice;
	}

	public void setInvoiceTaxPrice(Double invoiceTaxPrice) {
		this.invoiceTaxPrice = invoiceTaxPrice;
	}

	public String getSinvoiceDate() {
		return sinvoiceDate;
	}

	public void setSinvoiceDate(String sinvoiceDate) {
		this.sinvoiceDate = sinvoiceDate;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}
