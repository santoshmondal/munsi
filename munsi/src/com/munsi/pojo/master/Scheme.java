package com.munsi.pojo.master;

import java.io.Serializable;

public class Scheme implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String type;
	private Float percentdiscount;
	private Float freeItem;
	private Float requiredQuantity;
	private Float requiredAmount;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Float getPercentdiscount() {
		return percentdiscount;
	}
	public void setPercentdiscount(Float percentdiscount) {
		this.percentdiscount = percentdiscount;
	}
	public Float getFreeItem() {
		return freeItem;
	}
	public void setFreeItem(Float freeItem) {
		this.freeItem = freeItem;
	}
	public Float getRequiredQuantity() {
		return requiredQuantity;
	}
	public void setRequiredQuantity(Float requiredQuantity) {
		this.requiredQuantity = requiredQuantity;
	}
	public Float getRequiredAmount() {
		return requiredAmount;
	}
	public void setRequiredAmount(Float requiredAmount) {
		this.requiredAmount = requiredAmount;
	}
	
	
	
}
