package com.isdc.simulations;


public class UserInfo {

	public String _id;
	private String username;
	private String mobileno;
	private String address;

	public UserInfo() {
		super();
		System.out.println("Cons tructor user info");
	}

	public UserInfo(String username, String mobileno) {
		super();
		this.username = username;
		this.mobileno = mobileno;
	}

	public String getUsername() {
		System.out.println("get username");
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		System.out.println("set username");
	}

	public String getMobileno() {
		System.out.println("get mo");
		return mobileno;
	}

	public String getMobilenoTest() {
		System.out.println("get mot");
		return mobileno;
	}
	
	public void setMobileno(String mobileno) {
		System.out.println("set mo");
		this.mobileno = mobileno;
	}

}
