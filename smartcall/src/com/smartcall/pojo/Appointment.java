package com.smartcall.pojo;

import java.util.Date;

public class Appointment extends BasePojo {
	private static final long serialVersionUID = 1L;
	
	private String _id;
	private CustomerDetails customerDetails;
	private Date appimentDate;
	
	/** For values refer appoiment status ENUM */
	private String status;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public Date getAppimentDate() {
		return appimentDate;
	}

	public void setAppimentDate(Date appimentDate) {
		this.appimentDate = appimentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
