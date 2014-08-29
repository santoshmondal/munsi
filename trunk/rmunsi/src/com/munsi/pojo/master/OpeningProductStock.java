package com.munsi.pojo.master;

import java.io.Serializable;
import java.util.Date;

import com.munsi.pojo.BasePojo;

public class OpeningProductStock extends BasePojo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String _id;
	private Integer quantity;
	private String batchNumber;
	private Date expDate;
	private Date mfgDate;

	private String sExpDate;
	private String sMfgDate;

	private Product product;

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

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getsExpDate() {
		return sExpDate;
	}

	public void setsExpDate(String sExpDate) {
		this.sExpDate = sExpDate;
	}

	public String getsMfgDate() {
		return sMfgDate;
	}

	public void setsMfgDate(String sMfgDate) {
		this.sMfgDate = sMfgDate;
	}

}
