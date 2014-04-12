package com.estudio.pojo;

import java.util.Date;

public class Invoice extends BasePojo {

	private static final long serialVersionUID = 1L;

	private String _id;
	private String invoiceNumber;
	private Date invoiceDate;
	private Float totalAmount;
	private Float advanceBal;
	private Float remainingBal;
	private Date delivaryDate;
	private String status;
	private Customer customer;
	
	private PhotoDetails photoDetails;
	private FrameDetails frameDetails;
	private LaminationDetails laminationDetails;
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public PhotoDetails getPhotoDetails() {
		return photoDetails;
	}
	public void setPhotoDetails(PhotoDetails photoDetails) {
		this.photoDetails = photoDetails;
	}
	public FrameDetails getFrameDetails() {
		return frameDetails;
	}
	public void setFrameDetails(FrameDetails frameDetails) {
		this.frameDetails = frameDetails;
	}
	public LaminationDetails getLaminationDetails() {
		return laminationDetails;
	}
	public void setLaminationDetails(LaminationDetails laminationDetails) {
		this.laminationDetails = laminationDetails;
	}
}
