package com.munsi.pojo.master;

import java.io.Serializable;
import java.util.Date;

public class ProductBatch implements Serializable {

	private static final long serialVersionUID = 1L;

	private String batchNumber;
	private Date manufactureDate;
	private Date expiryDate;
	private Long batchCurrentStock;

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

}
