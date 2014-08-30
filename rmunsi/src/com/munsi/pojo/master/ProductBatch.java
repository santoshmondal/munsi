package com.munsi.pojo.master;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.munsi.util.CommonUtil;

public class ProductBatch implements Serializable {

	private static final long serialVersionUID = 1L;

	private String batchNumber;

	private Float purchaseRate;
	private Float mrp;
	private Float salesRate;

	private Long batchCurrentStock;

	private Date manufactureDate;
	private Date expiryDate;

	private String sMfgdate;
	private String sExpdate;

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
		if (manufactureDate != null) {
			this.sMfgdate = CommonUtil.longToStringDate(manufactureDate.getTime(), CommonUtil.DATE_FORMAT_ddMMyyyy_HYPHEN);
		}
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
		if (expiryDate != null) {
			this.sExpdate = CommonUtil.longToStringDate(expiryDate.getTime(), CommonUtil.DATE_FORMAT_ddMMyyyy_HYPHEN);
		}
	}

	public Long getBatchCurrentStock() {
		return batchCurrentStock;
	}

	public void setBatchCurrentStock(Long batchCurrentStock) {
		this.batchCurrentStock = batchCurrentStock;
	}

	public Float getPurchaseRate() {
		return purchaseRate;
	}

	public void setPurchaseRate(Float purchaseRate) {
		this.purchaseRate = purchaseRate;
	}

	public Float getMrp() {
		return mrp;
	}

	public void setMrp(Float mrp) {
		this.mrp = mrp;
	}

	public Float getSalesRate() {
		return salesRate;
	}

	public void setSalesRate(Float saleRate) {
		this.salesRate = saleRate;
	}

	public String getsMfgdate() {
		return sMfgdate;
	}

	public void setsMfgdate(String sMfgdate) {
		this.sMfgdate = sMfgdate;
	}

	public String getsExpdate() {
		return sExpdate;
	}

	public void setsExpdate(String sExpdate) {
		this.sExpdate = sExpdate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchNumber == null) ? 0 : batchNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductBatch other = (ProductBatch) obj;
		if (batchNumber == null) {
			if (other.batchNumber != null)
				return false;
		} else if (!batchNumber.equals(other.batchNumber))
			return false;
		return true;
	}

	public static void main(String[] args) {
		ProductBatch pb = new ProductBatch();
		pb.setBatchNumber("abc");
		pb.setMrp(12.22f);
		Set<ProductBatch> spb = new HashSet<>();
		spb.add(pb);
		ProductBatch pba = new ProductBatch();
		pba.setBatchNumber("abc");

		System.out.println(spb.contains(pba));

	}
}
