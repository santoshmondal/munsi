package com.estudio.pojo;

import java.io.Serializable;

public class PhotoDetails implements Serializable {
	//Note: No need to extends BasePojo
	private static final long serialVersionUID = 1L;

	private String photoNumber;
	
	/** Camera, SoftCopy, Scan */
	private String photoSource;
	
	private String size;
	
	private String price;
	
	private Integer quantity;

	public String getPhotoNumber() {
		return photoNumber;
	}

	public void setPhotoNumber(String photoNumber) {
		this.photoNumber = photoNumber;
	}

	public String getPhotoSource() {
		return photoSource;
	}

	public void setPhotoSource(String photoSource) {
		this.photoSource = photoSource;
	}

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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
}
