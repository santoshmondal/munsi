package com.munsi.pojo.master;

import com.munsi.pojo.BasePojo;

public class Customer extends BasePojo {
	private static final long serialVersionUID = 1L;
	
	private String _id;
	private String name;
	private String address;
	private String pin;
	private String mobile;
	private String phone;
	private String category;
	private String customerClass;
	private String blackList;
	private Float discount;
	private Integer creditDays;
	private Double creditLimit;
	private Area area;
	private Beat beat;
	private Double openingBalance;
	private MainAccount mainAccount;
	
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

	public MainAccount getMainAccount() {
		return mainAccount;
	}

	public void setMainAccount(MainAccount mainAccount) {
		this.mainAccount = mainAccount;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public Integer getCreditDays() {
		return creditDays;
	}

	public void setCreditDays(Integer creditDays) {
		this.creditDays = creditDays;
	}

	public Double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Beat getBeat() {
		return beat;
	}

	public void setBeat(Beat beat) {
		this.beat = beat;
	}
	
	
	public String getBlackList() {
		return blackList;
	}

	public void setBlackList(String blackList) {
		this.blackList = blackList;
	}
	
	public Double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
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
