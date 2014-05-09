package com.estudio.wrapper;

import java.util.Date;

public class InvoiceFlat {
	private String invoiceNumber;
	private Date invoiceDate;
	private Float invoiceTotalAmount;
	private Float invoiceAdvanceBal;
	private Float invoiceRemainingBal;
	private Date invoiceDelivaryDate;
	private String invoiceStatus;

	private String customerName;
	private String customerAddress;
	private Date customerMarriageDate;
	private Date customerDOB;
	private String customerEmailId;

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

	public Float getInvoiceTotalAmount() {
		return invoiceTotalAmount;
	}

	public void setInvoiceTotalAmount(Float invoiceTotalAmount) {
		this.invoiceTotalAmount = invoiceTotalAmount;
	}

	public Float getInvoiceAdvanceBal() {
		return invoiceAdvanceBal;
	}

	public void setInvoiceAdvanceBal(Float invoiceAdvanceBal) {
		this.invoiceAdvanceBal = invoiceAdvanceBal;
	}

	public Float getInvoiceRemainingBal() {
		return invoiceRemainingBal;
	}

	public void setInvoiceRemainingBal(Float invoiceRemainingBal) {
		this.invoiceRemainingBal = invoiceRemainingBal;
	}

	public Date getInvoiceDelivaryDate() {
		return invoiceDelivaryDate;
	}

	public void setInvoiceDelivaryDate(Date invoiceDelivaryDate) {
		this.invoiceDelivaryDate = invoiceDelivaryDate;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Date getCustomerMarriageDate() {
		return customerMarriageDate;
	}

	public void setCustomerMarriageDate(Date customerMarriageDate) {
		this.customerMarriageDate = customerMarriageDate;
	}

	public Date getCustomerDOB() {
		return customerDOB;
	}

	public void setCustomerDOB(Date customerDOB) {
		this.customerDOB = customerDOB;
	}

	public String getCustomerEmailId() {
		return customerEmailId;
	}

	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}

}
