package com.estudio.pojo.master;

import java.io.Serializable;


public class MasterProperties implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Float price;
	
	private Float direct;
	private Float mount;
	private Float rightMount;
	private Float leftMount;
	private Float goldMount;
	private Float pastting;
	
	
	public Float getPrice() {
		return price;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}
		
}
