package com.estudio.pojo;

import java.util.Date;

public class Customer extends BasePojo {
	private static final long serialVersionUID = 1L;
	
	/** Mobile number */
	private String _id;
	private String name;
	private String address;
	private Date marriageDate;
	private Date dob;
	private String emailId;
	
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

	public Date getMarriageDate() {
		return marriageDate;
	}

	public void setMarriageDate(Date marriageDate) {
		this.marriageDate = marriageDate;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
