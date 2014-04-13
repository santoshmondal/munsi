package com.estudio.pojo;

import java.util.Map;


public class Master extends BasePojo  {
	private static final long serialVersionUID = 1L;

	/** Photo, Frame, Lamination */
	private String _id;
	
	private String size;
	
	/** True or False in case of Frame only */
	private Boolean withStand;
	
	private Map<String, String> qualityPriceMap;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
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
