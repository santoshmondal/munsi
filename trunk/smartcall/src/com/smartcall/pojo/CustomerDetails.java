package com.smartcall.pojo;

import java.util.Date;
import java.util.Set;

public class CustomerDetails extends BasePojo{
	
	private static final long serialVersionUID = 1L;

	private String _id;
	
	private PersonalInfo personalInfo;
	
	private VehicleInfo vehicleInfo;
	
	private Set<Service> serviceList ;
	
	private Date lastServiceDate;
	
	private Date lastCallingDate;
	
	private AccessUser lastContactedBy;
	
	private String lastCallResponse;
	
	private String remark;
	
	private AccessUser assignedCaller;
	
	private Integer rating;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public VehicleInfo getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(VehicleInfo vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	public Set<Service> getServiceList() {
		return serviceList;
	}

	public void setServiceList(Set<Service> serviceList) {
		this.serviceList = serviceList;
	}

	public Date getLastServiceDate() {
		return lastServiceDate;
	}

	public void setLastServiceDate(Date lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}

	public Date getLastCallingDate() {
		return lastCallingDate;
	}

	public void setLastCallingDate(Date lastCallingDate) {
		this.lastCallingDate = lastCallingDate;
	}

	public AccessUser getLastContactedBy() {
		return lastContactedBy;
	}

	public void setLastContactedBy(AccessUser lastContactedBy) {
		this.lastContactedBy = lastContactedBy;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getLastCallResponse() {
		return lastCallResponse;
	}

	public void setLastCallResponse(String lastCallResponse) {
		this.lastCallResponse = lastCallResponse;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public AccessUser getAssignedCaller() {
		return assignedCaller;
	}

	public void setAssignedCaller(AccessUser assignedCaller) {
		this.assignedCaller = assignedCaller;
	}
		
}
