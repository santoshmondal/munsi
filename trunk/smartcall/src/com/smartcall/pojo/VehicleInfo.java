package com.smartcall.pojo;

import java.io.Serializable;
import java.util.Date;

public class VehicleInfo implements Serializable{
	//Note: No need to extends BasePojo
	private static final long serialVersionUID = 1L;
	
	private String salesConsultant;
	private String bookingOrderNo;
	private Date bookingOrderDate;
	private String model;
	private String variant;
	private String color;
	private String vin;
	private String engineNo;
	private String vehicleNo;
	private Date invoiceDate;
	private Date deliveryDate;
	private String dealerName;
	private String dealerState;
	private String dealerCity;
	private String dealerRegion;
	
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getVariant() {
		return variant;
	}
	public void setVariant(String variant) {
		this.variant = variant;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getBookingOrderNo() {
		return bookingOrderNo;
	}
	public void setBookingOrderNo(String bookingOrderNo) {
		this.bookingOrderNo = bookingOrderNo;
	}
	public Date getBookingOrderDate() {
		return bookingOrderDate;
	}
	public void setBookingOrderDate(Date bookingOrderDate) {
		this.bookingOrderDate = bookingOrderDate;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getDealerState() {
		return dealerState;
	}
	public void setDealerState(String dealerState) {
		this.dealerState = dealerState;
	}
	public String getDealerCity() {
		return dealerCity;
	}
	public void setDealerCity(String dealerCity) {
		this.dealerCity = dealerCity;
	}
	public String getDealerRegion() {
		return dealerRegion;
	}
	public void setDealerRegion(String dealerRegion) {
		this.dealerRegion = dealerRegion;
	}
	public String getSalesConsultant() {
		return salesConsultant;
	}
	public void setSalesConsultant(String salesConsultant) {
		this.salesConsultant = salesConsultant;
	}
	
	
	
	
}
