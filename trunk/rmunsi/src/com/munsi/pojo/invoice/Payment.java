package com.munsi.pojo.invoice;

public class Payment {

	private String paymentMode;
	private String cardNumber;
	private float paidAmount;
	private float balanceAmount;

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public float getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(float paidAmount) {
		this.paidAmount = paidAmount;
	}

	public float getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(float balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
}
