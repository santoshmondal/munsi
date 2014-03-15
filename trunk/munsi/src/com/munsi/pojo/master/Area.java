package com.munsi.pojo.master;

import java.util.Set;

import com.munsi.pojo.BasePojo;

public class Area extends BasePojo {

	private static final long serialVersionUID = 1L;

	private String _id;
	private String name;
	private String description;
	private String city;
	private String state;
	private String country;

	private Set<Beat> beatList;

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<Beat> getBeatList() {
		return beatList;
	}

	public void setBeatList(Set<Beat> beatList) {
		this.beatList = beatList;
	}

	@Override
	public int hashCode() {
		return get_id().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Area area = (Area) obj;
		if (get_id().equals(area.get_id())) {
			return true;
		}
		return false;
	}

}
