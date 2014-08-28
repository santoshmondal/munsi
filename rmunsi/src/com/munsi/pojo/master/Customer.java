package com.munsi.pojo.master;

import com.munsi.pojo.BasePojo;

public class Customer extends BasePojo {
	private static final long serialVersionUID = 1L;

	private String _id;
	private String address;
	private String city;
	private String name;
	private String pin;
	private String mobile;
	private String phone;
	private String email;
	private Integer creditDays;
	private Float creditLimit;
	private Float outStandingAmount;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCreditDays() {
		return creditDays;
	}

	public void setCreditDays(Integer creditDays) {
		this.creditDays = creditDays;
	}

	public Float getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Float creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Override
	public int hashCode() {
		return get_id().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Customer customer = (Customer) obj;
		if (get_id().equals(customer.get_id())) {
			return true;
		}
		return false;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Float getOutStandingAmount() {
		return outStandingAmount;
	}

	public void setOutStandingAmount(Float outStandingAmount) {
		this.outStandingAmount = outStandingAmount;
	}
}
