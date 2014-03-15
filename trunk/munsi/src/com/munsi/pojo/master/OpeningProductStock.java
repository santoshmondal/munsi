package com.munsi.pojo.master;

import java.io.Serializable;
import java.util.Date;

public class OpeningProductStock implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer quantity;
	private String batchNumber;
	private Date expDate;
	private Date mfgDate;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public Date getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}

}
