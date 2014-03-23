package com.munsi.pojo.master;

import java.io.Serializable;
import java.util.Date;

import com.munsi.pojo.BasePojo;

public class ProductScheme extends BasePojo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String _id;
	
	/** Scheme Name **/
	private String name;
	
	/** "1" - Free Item or "2" Percent Discount */
	private String schemeType;
	
	/** According to type it can be item discount or it can be price discount in % */
	private Float schemeValue;

	/** schemeON can be "1" for ITEM or "2" for AMOUNT */
	private String schemeOn;

	/** According to schemeON  it can be min sale item or it can be min sale amount */
	private Float minEligibleValue;
	
	/** Scheme valid till */
	private Date validTill;
	
	/** True for activ , false for inactive */
	private Boolean status;

	private Product product;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchemeType() {
		return schemeType;
	}

	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}

	public Float getSchemeValue() {
		return schemeValue;
	}

	public void setSchemeValue(Float schemeValue) {
		this.schemeValue = schemeValue;
	}

	public String getSchemeOn() {
		return schemeOn;
	}

	public void setSchemeOn(String schemeOn) {
		this.schemeOn = schemeOn;
	}

	public Float getMinEligibleVale() {
		return minEligibleValue;
	}

	public void setMinEligibleVale(Float minEligibleVale) {
		this.minEligibleValue = minEligibleVale;
	}

	public Date getValidTill() {
		return validTill;
	}

	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
		
	
}
