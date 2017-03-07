package com.cmpe202.g62.notification;

import com.cmpe202.g62.model.RideDetails;

/**
 * This class is concrete observer of observer pattern and used to observe member
 *
 */
public class PaymentObserver extends Observer {

	/**
	 * This method updates member of payment notifications
	 */
	@Override
	public void update(Notifier subject, Object object, String message) {
		System.out.println("=================================================");
		if(object instanceof RideDetails){
			RideDetails rideDetails = (RideDetails)object;
			if(message.equalsIgnoreCase("paid")){
				System.out.println("Dear "+rideDetails.getPassenger().getFirstName() +" "+ rideDetails.getPassenger().getLastName()+", your payment of $"+rideDetails.getRideAmount()+rideDetails.getParkingAmount() +" is successful");
			}
		}
		System.out.println("=================================================");
	}

}
