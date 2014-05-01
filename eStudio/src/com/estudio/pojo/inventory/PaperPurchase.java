package com.estudio.pojo.inventory;

import java.util.Date;

import com.estudio.pojo.BasePojo;

public class PaperPurchase extends BasePojo {

	private static final long serialVersionUID = 1L;
	
	private String _id;
	private String quality;
	private String quantity;
	private String amount;
	private String vender;
	private Date purchaseData;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getVender() {
		return vender;
	}
	public void setVender(String vender) {
		this.vender = vender;
	}
	public Date getPurchaseData() {
		return purchaseData;
	}
	public void setPurchaseData(Date purchaseData) {
		this.purchaseData = purchaseData;
	}
	
	
	
}
