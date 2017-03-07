package com.cmpe202.g62.payment.impl;

/**
 * This class is concrete implementation of template method's abstract class
 * It is used for paypal payment 
 *
 */
import java.text.ParseException;

import com.cmpe202.g62.dao.PaymentDAO;
import com.cmpe202.g62.model.PayPal;
import com.cmpe202.g62.model.Payment;
import com.cmpe202.g62.payment.PaymentManager;
import com.cmpe202.g62.util.CommonUtils;

public class PayPalPayment extends PaymentManager {
	
	private PaymentDAO paymentDAO;
	
	public PayPalPayment() {
		paymentDAO = new PaymentDAO();
	}

	/**
	 * This method checks validity of paypal details
	 * @param payment
	 * @return boolean
	 * @throws ParseException
	 */
	@Override
	public boolean checkValidity(Payment payment) throws ParseException{
		boolean valid = false;
		PayPal paypal = (PayPal)payment;
		valid = CommonUtils.validateDate(paypal.getValidDate()) && CommonUtils.validateEmail(paypal.getEmail());
		return valid;
	}

	/**
	 * This method authenticates the paypal details
	 * @param payment
	 * @return boolean
	 */
	@Override
	public boolean authenticatePaymentMode(Payment payment) {
		boolean valid = paymentDAO.validatePaypalDetails((PayPal)payment);
		return valid;
	}


}
