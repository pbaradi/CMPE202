package com.cmpe202.g62.payment;

import java.text.ParseException;

import com.cmpe202.g62.dao.PaymentDAO;
import com.cmpe202.g62.model.Payment;
import com.cmpe202.g62.model.RideDetails;

/**
 * THis class is the abstract class of template method pattern used for payment
 *
 */
public abstract class PaymentManager {

	public Payment addPaymentDetails(Payment payment){
		PaymentDAO paymentDAO = new PaymentDAO();
		payment = paymentDAO.addPaymentDetails(payment);
		return payment;
	}

	/**
	 * This method checks validity of payment details
	 * @param payment
	 * @return boolean
	 * @throws ParseException
	 */
	public abstract boolean checkValidity(Payment payment) throws ParseException;
	
	/**
	 * This method authenticates the payment details
	 * @param payment
	 * @return boolean
	 */
	public abstract boolean authenticatePaymentMode(Payment payment);

	/**
	 * This method makes the payment
	 * @param rideDetails
	 * @return int
	 * @throws ParseException
	 */
	public int pay(RideDetails rideDetails) throws ParseException{
		PaymentDAO paymentDAO = new PaymentDAO();
		int result=0;
		if(checkValidity(rideDetails.getPayment()) && authenticatePaymentMode(rideDetails.getPayment()))
			result = paymentDAO.makePayment(rideDetails);
		else
			System.out.println("Dear "+rideDetails.getPassenger().getFirstName()+" "+rideDetails.getPassenger().getLastName()+", please enter valid Paypal account details.");
		return result;
	}

}
