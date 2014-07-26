package com.munsi.pojo.master;

import java.util.Set;

import com.munsi.pojo.BasePojo;

public class Product extends BasePojo {

	private static final long serialVersionUID = 1L;

	private String _id;

	private String code;
	private String name;
	private String alias;
	private Float mrp;
	private Float costPrice;
	private Float sellPrice;
	private Float landingCost;
	
	private Float barCode;
	
	private Set<Tax> taxList;
	private Manufacturer manufacturer;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Float getMrp() {
		return mrp;
	}
	public void setMrp(Float mrp) {
		this.mrp = mrp;
	}
	public Float getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Float costPrice) {
		this.costPrice = costPrice;
	}
	public Float getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Float sellPrice) {
		this.sellPrice = sellPrice;
	}
	public Float getLandingCost() {
		return landingCost;
	}
	public void setLandingCost(Float landingCost) {
		this.landingCost = landingCost;
	}
	public Float getBarCode() {
		return barCode;
	}
	public void setBarCode(Float barCode) {
		this.barCode = barCode;
	}
	public Set<Tax> getTaxList() {
		return taxList;
	}
	public void setTaxList(Set<Tax> taxList) {
		this.taxList = taxList;
	}
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	
}
