package com.munsi.report;

import java.util.Date;

import com.munsi.pojo.BasePojo;
import com.munsi.pojo.master.Customer;

public class ReportSalesInvoice extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _id;
	private String invoiceNumber;

	private String customerName;
	private String customerMobile;
	private Float customerOutStandingAmount;
	private String customerPhone;

	private Date invoiceDate;
	private String sinvoiceDate;

	private Double invoiceDiscountPrice;
	private Double invoiceDiscountPercent;

	private Double invoiceTaxPercent;
	private Double invoiceTaxPrice;

	private Double sumOfNetPaybleProductPrice;

	private Double netPayblePrice;
	private Integer numberOfItem;
	private Double paidAmount;
	private Double freight;
	private Double roundOfAmount;

	// netPayblePrice - paidAmount
	private Double balanceAmount;

	// Default custructor
	public ReportSalesInvoice() {
	}

	public ReportSalesInvoice(String _id, String invoiceNumber, Customer customer, Date invoiceDate, String sinvoiceDate, Double invoiceDiscountPrice, Double invoiceDiscountPercent, Double invoiceTaxPercent, Double invoiceTaxPrice, Double sumOfNetPaybleProductPrice,
			Double netPayblePrice, Integer numberOfItem, Double paidAmount, Double freight, Double roundOfAmount, Double balanceAmount) {
		super();
		this._id = _id;
		this.invoiceNumber = invoiceNumber;
		this.invoiceDate = invoiceDate;
		this.sinvoiceDate = sinvoiceDate;
		this.invoiceDiscountPrice = invoiceDiscountPrice;
		this.invoiceDiscountPercent = invoiceDiscountPercent;
		this.invoiceTaxPercent = invoiceTaxPercent;
		this.invoiceTaxPrice = invoiceTaxPrice;
		this.sumOfNetPaybleProductPrice = sumOfNetPaybleProductPrice;
		this.netPayblePrice = netPayblePrice;
		this.numberOfItem = numberOfItem;
		this.paidAmount = paidAmount;
		this.freight = freight;
		this.roundOfAmount = roundOfAmount;
		this.balanceAmount = balanceAmount;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getSinvoiceDate() {
		return sinvoiceDate;
	}

	public void setSinvoiceDate(String sinvoiceDate) {
		this.sinvoiceDate = sinvoiceDate;
	}

	public Double getInvoiceDiscountPrice() {
		return invoiceDiscountPrice;
	}

	public void setInvoiceDiscountPrice(Double invoiceDiscountPrice) {
		this.invoiceDiscountPrice = invoiceDiscountPrice;
	}

	public Double getInvoiceDiscountPercent() {
		return invoiceDiscountPercent;
	}

	public void setInvoiceDiscountPercent(Double invoiceDiscountPercent) {
		this.invoiceDiscountPercent = invoiceDiscountPercent;
	}

	public Double getInvoiceTaxPercent() {
		return invoiceTaxPercent;
	}

	public void setInvoiceTaxPercent(Double invoiceTaxPercent) {
		this.invoiceTaxPercent = invoiceTaxPercent;
	}

	public Double getInvoiceTaxPrice() {
		return invoiceTaxPrice;
	}

	public void setInvoiceTaxPrice(Double invoiceTaxPrice) {
		this.invoiceTaxPrice = invoiceTaxPrice;
	}

	public Double getSumOfNetPaybleProductPrice() {
		return sumOfNetPaybleProductPrice;
	}

	public void setSumOfNetPaybleProductPrice(Double sumOfNetPaybleProductPrice) {
		this.sumOfNetPaybleProductPrice = sumOfNetPaybleProductPrice;
	}

	public Double getNetPayblePrice() {
		return netPayblePrice;
	}

	public void setNetPayblePrice(Double netPayblePrice) {
		this.netPayblePrice = netPayblePrice;
	}

	public Integer getNumberOfItem() {
		return numberOfItem;
	}

	public void setNumberOfItem(Integer numberOfItem) {
		this.numberOfItem = numberOfItem;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getRoundOfAmount() {
		return roundOfAmount;
	}

	public void setRoundOfAmount(Double roundOfAmount) {
		this.roundOfAmount = roundOfAmount;
	}

	public Double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public Float getCustomerOutStandingAmount() {
		return customerOutStandingAmount;
	}

	public void setCustomerOutStandingAmount(Float customerOutStandingAmount) {
		this.customerOutStandingAmount = customerOutStandingAmount;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

}
