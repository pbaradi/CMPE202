package com.cmpe202.g62.payment;

import java.text.ParseException;

import com.cmpe202.g62.model.Payment;
import com.cmpe202.g62.model.RideDetails;
import com.cmpe202.g62.notification.PaymentNotifier;
import com.cmpe202.g62.notification.PaymentObserver;
import com.cmpe202.g62.payment.impl.CreditCardPayment;
import com.cmpe202.g62.payment.impl.PayPalPayment;

/**
 * This class is client for templete method pattern used for payment
 *
 */
public class PaymentController{

	private PaymentManager paymentManager;
	private PaymentNotifier notifier;

	public PaymentController(){
		super();

		notifier = new PaymentNotifier();
		PaymentObserver observer = new PaymentObserver();
		notifier.addObserver(observer);
	}

	/**
	 * This method adds payment details
	 * @param payment
	 * @return Payment
	 */
	public Payment addPaymentDetails(Payment payment){
		if(payment.getPaymentMode().equals("creditCard"))
			paymentManager = new CreditCardPayment();
		else
			paymentManager = new PayPalPayment();
		payment =  paymentManager.addPaymentDetails(payment);
		return payment;
	}

	/**
	 * THis method makes payment 
	 * @param rideDetails
	 * @return int
	 */
	public int makePayment(RideDetails rideDetails){
		int result = 0;
		try{
			if(rideDetails.getPayment().getPaymentMode().equals("creditCard"))
				paymentManager = new CreditCardPayment();
			else
				paymentManager = new PayPalPayment();

			result = paymentManager.pay(rideDetails);
			if(result > 0){
				notifier.setRideDetails(rideDetails, "paid");
			}
		}catch(ParseException e){
			e.printStackTrace();
		}
		return result;
	}

}
