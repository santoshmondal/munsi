package com.estudio.pojo;

import java.util.Date;
import java.util.List;

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

	private List<PhotoDetails> photoDetailsList;
	private List<FrameDetails> frameDetailsList;
	private List<LaminationDetails> laminationDetailsList;

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

	public List<PhotoDetails> getPhotoDetailsList() {
		return photoDetailsList;
	}

	public void setPhotoDetailsList(List<PhotoDetails> photoDetailsList) {
		this.photoDetailsList = photoDetailsList;
	}

	public List<FrameDetails> getFrameDetailsList() {
		return frameDetailsList;
	}

	public void setFrameDetailsList(List<FrameDetails> frameDetailsList) {
		this.frameDetailsList = frameDetailsList;
	}

	public List<LaminationDetails> getLaminationDetailsList() {
		return laminationDetailsList;
	}

	public void setLaminationDetailsList(List<LaminationDetails> laminationDetailsList) {
		this.laminationDetailsList = laminationDetailsList;
	}

}
