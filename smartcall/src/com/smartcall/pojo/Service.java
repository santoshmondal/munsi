package com.smartcall.pojo;

import java.io.Serializable;

public class Service implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private String date;
	private String callingDate;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCallingDate() {
		return callingDate;
	}
	public void setCallingDate(String callingDate) {
		this.callingDate = callingDate;
	}
	
}
