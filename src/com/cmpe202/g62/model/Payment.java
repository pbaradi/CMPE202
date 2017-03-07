package com.cmpe202.g62.model;

import java.util.Date;

public class Payment{
	private int paymentId;
	private Date validDate;
	private String paymentMode;
	private Member member;
	
	public int getPaymentId() {
		return paymentId;
	}
	public Payment(int paymentId, Date validDate) {
		super();
		this.paymentId = paymentId;
		this.validDate = validDate;
	}
	public Payment(int paymentId, Date validDate, String paymentMode) {
		super();
		this.paymentId = paymentId;
		this.validDate = validDate;
		this.paymentMode = paymentMode;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Payment(int paymentId, Date validDate, String paymentMode,
			Member member) {
		super();
		this.paymentId = paymentId;
		this.validDate = validDate;
		this.paymentMode = paymentMode;
		this.member = member;
	}
	
	
}
