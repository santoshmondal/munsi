package com.munsi.pojo.master;

import com.munsi.pojo.BasePojo;



public class MainAccount extends BasePojo {
	
	private static final long serialVersionUID = 1L;
	
	private String _id;
	private String code;
	private String location;
	private String name;
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
}
