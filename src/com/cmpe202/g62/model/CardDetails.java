package com.cmpe202.g62.model;

import java.util.Date;

public class CardDetails extends Payment {
	private long cardNumber;
	
	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	

	public CardDetails(int paymentId, Date validDate, String paymentMode, Member member, long cardNumber) {
		super(paymentId, validDate, paymentMode, member);
		// TODO Auto-generated constructor stub
		this.cardNumber = cardNumber;
	}

	public CardDetails(int paymentId, Date validDate, String paymentMode) {
		super(paymentId, validDate, paymentMode);
		// TODO Auto-generated constructor stub
	}

	public CardDetails(int paymentId, Date validDate) {
		super(paymentId, validDate);
		// TODO Auto-generated constructor stub
	}

	public CardDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

}
