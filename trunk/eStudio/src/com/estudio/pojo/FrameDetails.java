package com.estudio.pojo;

import java.io.Serializable;

public class FrameDetails implements Serializable {
	//Note: No need to extends BasePojo
	private static final long serialVersionUID = 1L;
	
	private String frameNumber;
	
	/** Camera, SoftCopy, Scan */
	private String photoSource;
	
	private String size;
	
	private String price;
	
	/** Glass or Metal */
	private String quality;
	
	private Integer quantity;

	private String remark;
	
	private String frameType;

	
	public String getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(String frameNumber) {
		this.frameNumber = frameNumber;
	}

	public String getFrameType() {
		return frameType;
	}

	public void setFrameType(String frameType) {
		this.frameType = frameType;
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
