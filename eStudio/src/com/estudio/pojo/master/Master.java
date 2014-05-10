package com.estudio.pojo.master;

import com.estudio.pojo.BasePojo;

public class Master extends BasePojo  {
	private static final long serialVersionUID = 1L;

	private String _id;
	
	private String type;
	private String size;
	private String quality;
	private Float price;

	private Float direct;
	private Float mount;
	private Float rightMount;
	private Float leftMount;
	private Float goldMount;
	private Float pastting;
	private String description;

	public String get_id() {
		return _id;
	}
	
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getDirect() {
		return direct;
	}
	public void setDirect(Float direct) {
		this.direct = direct;
	}
	public Float getMount() {
		return mount;
	}
	public void setMount(Float mount) {
		this.mount = mount;
	}
	public Float getRightMount() {
		return rightMount;
	}
	public void setRightMount(Float rightMount) {
		this.rightMount = rightMount;
	}
	public Float getLeftMount() {
		return leftMount;
	}
	public void setLeftMount(Float leftMount) {
		this.leftMount = leftMount;
	}
	public Float getGoldMount() {
		return goldMount;
	}
	public void setGoldMount(Float goldMount) {
		this.goldMount = goldMount;
	}
	public Float getPastting() {
		return pastting;
	}
	public void setPastting(Float pastting) {
		this.pastting = pastting;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}

