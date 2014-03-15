package com.isdc.simulations;

public class UserInfo {

	private String username;
	private String mobileno;

	public UserInfo() {
		super();
	}

	public UserInfo(String username, String mobileno) {
		super();
		this.username = username;
		this.mobileno = mobileno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

}
