package com.smartcall.pojo;

import java.io.Serializable;

public class PersonalInfo implements Serializable{
	//Note: No need to extends BasePojo
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String residentialAddress;
	private String mobile;
	private String residencePhone;
	private String officePhone;
	private String emailId;
	
	private String companyName;
	private String companyAddress;
	private String city;
	private String state;
	private String pin;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResidentialAddress() {
		return residentialAddress;
	}
	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getResidencePhone() {
		return residencePhone;
	}
	public void setResidencePhone(String residencePhone) {
		this.residencePhone = residencePhone;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getEmailId() {
		return emailId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}
