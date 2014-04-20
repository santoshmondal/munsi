package com.estudio.pojo;

import java.io.Serializable;

public class LaminationDetails implements Serializable {
	//Note: No need to extends BasePojo
	private static final long serialVersionUID = 1L;

	private String size;
	
	private String price;
	
	/** Glass or Metal */
	private String quality;

	private Integer quantity;

	private String remark;
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
