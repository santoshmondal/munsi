package com.estudio.pojo;

import java.util.Date;

public class FlatInvoice extends BasePojo {

	private static final long serialVersionUID = 1L;

	private String _id;
	private String invoiceNumber;
	private Date invoiceDate;
	private Float totalAmount;
	private Float advanceBal;
	private Float remainingBal;
	private Date delivaryDate;
	private String status;
	private String sInvoiceDate;
	private String sDelivaryDate;

	private String customerName;
	private String customerPhone;
	private String photoNo;

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

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Float getAdvanceBal() {
		return advanceBal;
	}

	public void setAdvanceBal(Float advanceBal) {
		this.advanceBal = advanceBal;
	}

	public Float getRemainingBal() {
		return remainingBal;
	}

	public void setRemainingBal(Float remainingBal) {
		this.remainingBal = remainingBal;
	}

	public Date getDelivaryDate() {
		return delivaryDate;
	}

	public void setDelivaryDate(Date delivaryDate) {
		this.delivaryDate = delivaryDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getsInvoiceDate() {
		return sInvoiceDate;
	}

	public void setsInvoiceDate(String sInvoiceDate) {
		this.sInvoiceDate = sInvoiceDate;
	}

	public String getsDelivaryDate() {
		return sDelivaryDate;
	}

	public void setsDelivaryDate(String sDelivaryDate) {
		this.sDelivaryDate = sDelivaryDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getPhotoNo() {
		return photoNo;
	}

	public void setPhotoNo(String photoNo) {
		this.photoNo = photoNo;
	}

}