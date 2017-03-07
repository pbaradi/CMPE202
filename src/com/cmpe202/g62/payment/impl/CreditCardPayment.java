package com.cmpe202.g62.payment.impl;

import java.text.ParseException;

import com.cmpe202.g62.dao.PaymentDAO;
import com.cmpe202.g62.model.CardDetails;
import com.cmpe202.g62.model.Payment;
import com.cmpe202.g62.payment.PaymentManager;
import com.cmpe202.g62.util.CommonUtils;

/**
 * This class is concrete implementation of template method's abstract class
 * It is used for credit card payment 
 *
 */
public class CreditCardPayment extends PaymentManager {
	PaymentDAO paymentDAO;
	
	public CreditCardPayment() {
		// TODO Auto-generated constructor stub
		paymentDAO = new PaymentDAO();
	}
	
	/**
	 * This method checks validity of credit card details
	 * @param payment
	 * @return boolean
	 * @throws ParseException
	 */
	@Override
	public boolean checkValidity(Payment payment) throws ParseException{
		Boolean valid;
		CardDetails card = (CardDetails)payment;
		valid = CommonUtils.validateDate(card.getValidDate()) && CommonUtils.validateCaardLength(card.getCardNumber());
		return valid;
	}


	/**
	 * This method authenticates the credit card details
	 * @param payment
	 * @return boolean
	 */
	@Override
	public boolean authenticatePaymentMode(Payment payment) {
		boolean valid = paymentDAO.validateCardDetails((CardDetails)payment);
		return valid;
	}


}

