package com.estudio.pojo.inventory;

import java.io.Serializable;

public class PaperInventory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer quantity;
	private String quality;
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getQuality() {
		return quality;
	}
	// "metal" or "glass"
	public void setQuality(String quality) {
		this.quality = quality;
	}

	
}
