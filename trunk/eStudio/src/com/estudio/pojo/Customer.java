package com.estudio.pojo;

public class Customer extends BasePojo {
	private static final long serialVersionUID = 1L;
	
	private String _id;
	private String name;
	private String address;
	private String pin;
	private String mobile;
	private String phone;
	private String city;
	private String state;
	private String customerClass;
	private Boolean blackList;
	private Float discount;
	
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

	public String getCustomerClass() {
		return customerClass;
	}

	public void setCustomerClass(String customerClass) {
		this.customerClass = customerClass;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	
	
	public Boolean getBlackList() {
		return blackList;
	}

	public void setBlackList(Boolean blackList) {
		this.blackList = blackList;
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

	@Override
	public int hashCode() {
		return get_id().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		Customer customer = (Customer) obj;
		if( get_id().equals(customer.get_id()) ){
			return true;
		}
		return false;
	}
}
