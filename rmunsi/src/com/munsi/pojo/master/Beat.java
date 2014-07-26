package com.munsi.pojo.master;

import com.munsi.pojo.BasePojo;


public class Beat extends BasePojo {

	private static final long serialVersionUID = 1L;

	private String _id;
	private String name;
	private String description;
	private Area area;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Override
	public int hashCode() {
		return get_id().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Customer cus = (Customer) obj;
		if (get_id().equals(cus.get_id())) {
			return true;
		}
		return false;
	}
}
