package com.cmpe202.g62.ride.impl;

import java.util.List;

import com.cmpe202.g62.model.Request;
import com.cmpe202.g62.model.RideDetails;
import com.cmpe202.g62.notification.RideNotifier;
import com.cmpe202.g62.notification.RideObserver;
import com.cmpe202.g62.payment.PaymentController;
import com.cmpe202.g62.ride.RideInterface;
import com.cmpe202.g62.ride.State;

/**
 * This class is one of the concrete states of State pattern
 *
 */
public class CompletingState implements State {
	RideInterface rideState;
	RideNotifier rideNotifier;
	private PaymentController paymentController;

	/**
	 * Constructor initializes the objects  
	 * @param request
	 */
	public CompletingState(RideInterface request) {
		super();
		this.rideState = request;

		rideNotifier = new RideNotifier();
		RideObserver rideObserver = new RideObserver();
		rideNotifier.addObserver(rideObserver);

		paymentController = new PaymentController();
	}
	
	/**
	 * This method notifies that ride is already requested
	 * @param request
	 * @return Request
	 */
	@Override
	public Request receiveRequest(Request request) {
		rideNotifier.setRide(request.getRide(), "RequestedRide");
		return request;
	}

	/**
	 * This method notifies that ride request is already processed
	 * @param request
	 * @return Request
	 */
	@Override
	public Request processRequest(Request request) {
		rideNotifier.setRide(request.getRide(), "processedRide");
		return request;
	}

	/**
	 * THis method completes the ride
	 */
	@Override
	public Request completeRequest(Request request) {
		List<RideDetails> rideDetailsList = request.getRide().getRideDetailsList();
		
		//Iterates through multiple rides and makes payment for each ride
		for (RideDetails rideDetails : rideDetailsList) {
			
			//makes payment 
			int result = paymentController.makePayment(rideDetails);
			
			//notifies member if payment is successful
			if(result>0){
				rideNotifier.setRide(request.getRide(), "completeRide");
				rideState.setState(new WaitingState(rideState));
			}else
				rideState.setState(new StartingState(rideState));
				
		}
		
		if(request.getRideType().equalsIgnoreCase("future")){
			rideState.setState(new WaitingState(rideState));
		}
		
		return request;
	}

	

}
