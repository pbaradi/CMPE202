package com.cmpe202.g62.model;

import java.util.Date;

public class PayPal extends Payment {
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public PayPal(String email, String password){
		this.email = email;
		this.password = password;
	}
	
	
	public PayPal(int paymentId, Date validDate, String paymentMode, Member member, String email, String password) {
		super(paymentId, validDate, paymentMode, member);
		// TODO Auto-generated constructor stub
		this.email = email;
		this.password = password;
	}
	public PayPal(int paymentId, Date validDate, String paymentMode) {
		super(paymentId, validDate, paymentMode);
		// TODO Auto-generated constructor stub
	}
	public PayPal(int paymentId, Date validDate) {
		super(paymentId, validDate);
		// TODO Auto-generated constructor stub
	}
	public PayPal() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
