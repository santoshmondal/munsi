package com.munsi.pojo.master;

import java.util.Set;

import com.munsi.pojo.BasePojo;

public class Product extends BasePojo {

	private static final long serialVersionUID = 1L;

	private String _id;

	private String code;
	private String name;
	private String alias;
	private Float weight;
	private Float margin;
	private Float mrp;
	private Float purchaseRate;
	private String purchaseUnit;

	private Float salesRate;
	private String salesUnit;

	/** Number of item in a BOX (if Purchase unit is box) */
	private Integer pack;
	private String lockItem;

	/** way of tax calculation */
	private String vatType;
	private Set<Tax> taxList;
	private Manufacturer manufacturer;
	private ProductGroup productGroup;
	private ProductGroup productSubGroup;
	private Float serviceCharge;

	private String barCode;
	private Boolean allowNegativeStock;
	private Integer minStock;
	private Integer maxStock;
	private Integer currentStock;

	private Set<ProductBatch> batchList;

	private Float derSumOfProudctTax;

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

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getMargin() {
		return margin;
	}

	public void setMargin(Float margin) {
		this.margin = margin;
	}

	public Float getMrp() {
		return mrp;
	}

	public void setMrp(Float mrp) {
		this.mrp = mrp;
	}

	public Float getPurchaseRate() {
		return purchaseRate;
	}

	public void setPurchaseRate(Float purchaseRate) {
		this.purchaseRate = purchaseRate;
	}

	public String getPurchaseUnit() {
		return purchaseUnit;
	}

	public void setPurchaseUnit(String purchaseUnit) {
		this.purchaseUnit = purchaseUnit;
	}

	public Float getSalesRate() {
		return salesRate;
	}

	public void setSalesRate(Float salesRate) {
		this.salesRate = salesRate;
	}

	public String getSalesUnit() {
		return salesUnit;
	}

	public void setSalesUnit(String salesUnit) {
		this.salesUnit = salesUnit;
	}

	public Integer getPack() {
		return pack;
	}

	public void setPack(Integer pack) {
		this.pack = pack;
	}

	public String getLockItem() {
		return lockItem;
	}

	public void setLockItem(String lockItem) {
		this.lockItem = lockItem;
	}

	public String getVatType() {
		return vatType;
	}

	public void setVatType(String vatType) {
		this.vatType = vatType;
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

	public ProductGroup getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(ProductGroup productGroup) {
		this.productGroup = productGroup;
	}

	public Float getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(Float serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public ProductGroup getProductSubGroup() {
		return productSubGroup;
	}

	public void setProductSubGroup(ProductGroup productSubGroup) {
		this.productSubGroup = productSubGroup;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Boolean getAllowNegativeStock() {
		return allowNegativeStock;
	}

	public void setAllowNegativeStock(Boolean allowNigativeStock) {
		this.allowNegativeStock = allowNigativeStock;
	}

	public Integer getMinStock() {
		return minStock;
	}

	public void setMinStock(Integer minStock) {
		this.minStock = minStock;
	}

	public Integer getMaxStock() {
		return maxStock;
	}

	public void setMaxStock(Integer maxStock) {
		this.maxStock = maxStock;
	}

	public Float getDerSumOfProudctTax() {
		return derSumOfProudctTax;
	}

	public void setDerSumOfProudctTax(Float derSumOfProudctTax) {
		this.derSumOfProudctTax = derSumOfProudctTax;
	}

	public Integer getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(Integer currentStock) {
		this.currentStock = currentStock;
	}

	public Set<ProductBatch> getBatchList() {
		return batchList;
	}

	public void setBatchList(Set<ProductBatch> batchList) {
		this.batchList = batchList;
	}

}
