package com.munsi.pojo.master;

import java.io.Serializable;
import java.util.Date;

public class Scheme implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** Scheme Name **/
	private String name;
	
	/** "1" - Free Item or "2" Percent Discount */
	private String schemeType;
	
	/** According to type it can be item discount or it can be price discount in % */
	private Float schemeValue;

	/** schemeON can be "1" for ITEM or "2" for AMOUNT */
	private String schemeOn;

	/** According to schemeON  it can be min sale item or it can be min sale amount */
	private Float minEligibleVale;
	
	/** Scheme valid till */
	private Date validTill;
	
	/** True for activ , false for inactive */
	private Boolean status;

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
		return minEligibleVale;
	}

	public void setMinEligibleVale(Float minEligibleVale) {
		this.minEligibleVale = minEligibleVale;
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
	
	
		
	
}
