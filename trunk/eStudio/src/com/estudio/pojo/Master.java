package com.estudio.pojo;

import java.util.Map;


public class Master extends BasePojo  {
	private static final long serialVersionUID = 1L;

	private String _id;
	
	/** Photo, Frame, Lamination */
	private String type;
	
	private String size;
	
	/** Estimated price */
	private String price;
	
	/** Glass or Metal in case of Frame and Lamination */
	private String quality;
	
	/** True or False in case of Frame only */
	private Boolean withStand;
	
	private Map<String, String> qualityPriceMap;

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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	/** Glass or Metal in case of Frame and Lamination Type*/
	public String getQuality() {
		return quality;
	}

	/** Glass or Metal in case of Frame and Lamination Type*/
	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	/** True or False in case of Frame Type only */
	public Boolean getWithStand() {
		return withStand;
	}

	/** True or False in case of Frame Type only */
	public void setWithStand(Boolean withStand) {
		this.withStand = withStand;
	}

	public Map<String, String> getQualityPriceMap() {
		return qualityPriceMap;
	}

	public void setQualityPriceMap(Map<String, String> qualityPriceMap) {
		this.qualityPriceMap = qualityPriceMap;
	}
	
	
}
