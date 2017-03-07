package com.cmpe202.g62.notification;

import com.cmpe202.g62.model.Payment;
import com.cmpe202.g62.model.RideDetails;

/**
 * This class is concrete implementor of subject of observer pattern and used for payment notifications
 *
 */
public class PaymentNotifier extends Notifier {
	
	private Payment payment;
	private RideDetails rideDetails;
	
	public PaymentNotifier() {
		super();
	}	

	public PaymentNotifier(Payment payment) {
		super();
		this.payment = payment;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment, String message) {
		this.payment = payment;
		notifyObservers(payment, message);
	}

	public RideDetails getRideDetails() {
		return rideDetails;
	}

	public void setRideDetails(RideDetails rideDetails, String message) {
		this.rideDetails = rideDetails;
		notifyObservers(rideDetails, message);
	}

}
