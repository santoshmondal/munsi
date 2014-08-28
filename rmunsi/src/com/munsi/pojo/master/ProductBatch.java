package com.munsi.pojo.master;

import java.io.Serializable;
import java.util.Date;

public class ProductBatch implements Serializable {

	private static final long serialVersionUID = 1L;

	private String batchNumber;

	private Double purchaseRate;
	private Double mrp;
	private Double saleRate;

	private Long batchCurrentStock;

	private Date manufactureDate;
	private Date expiryDate;

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Long getBatchCurrentStock() {
		return batchCurrentStock;
	}

	public void setBatchCurrentStock(Long batchCurrentStock) {
		this.batchCurrentStock = batchCurrentStock;
	}

	public Double getPurchaseRate() {
		return purchaseRate;
	}

	public void setPurchaseRate(Double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	public Double getSaleRate() {
		return saleRate;
	}

	public void setSaleRate(Double saleRate) {
		this.saleRate = saleRate;
	}

}
