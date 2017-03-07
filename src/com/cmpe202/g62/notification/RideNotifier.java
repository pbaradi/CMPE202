package com.cmpe202.g62.notification;

import com.cmpe202.g62.model.Ride;

/**
 * This class is concrete implementor of subject of observer pattern and used for ride notifications
 *
 */
public class RideNotifier extends Notifier {
	
	private Ride ride;
	
	public RideNotifier(Ride ride) {
		// TODO Auto-generated constructor stub
		this.ride = ride;
	}
	
	public RideNotifier() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Ride getRide() {
		return ride;
	}

	public void setRide(Ride ride, String message) {
		this.ride = ride;
		notifyObservers(ride, message);
	}

}
