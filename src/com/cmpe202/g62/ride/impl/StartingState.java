package com.cmpe202.g62.ride.impl;

import com.cmpe202.g62.dao.RideDAO;
import com.cmpe202.g62.dispatch.Dispatcher;
import com.cmpe202.g62.model.Request;
import com.cmpe202.g62.model.Ride;
import com.cmpe202.g62.notification.RideNotifier;
import com.cmpe202.g62.notification.RideObserver;
import com.cmpe202.g62.ride.RideInterface;
import com.cmpe202.g62.ride.State;

/**
 * This class is one of the concrete states of State pattern
 *
 */
public class StartingState implements State {
	RideInterface rideState;
	RideNotifier rideNotifier;
	RideDAO rideDAO;
	
	/**
	 * Constructor initializes the objects  
	 * @param request
	 */
	public StartingState(RideInterface ride) {
		super();
		this.rideState = ride;
		rideNotifier = new RideNotifier();
		RideObserver rideObserver = new RideObserver();
		rideNotifier.addObserver(rideObserver);
		
		rideDAO = new RideDAO();
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
	 * This method processes the ride and generates route
	 * @param request
	 * @return Request
	 */
	@Override
	public Request processRequest(Request request) {
		Ride ride = request.getRide();
		Dispatcher dispatcher = new Dispatcher();
		rideNotifier.setRide(ride, "ProcessingRide");
		if(ride!=null){
			//generates route
			request = dispatcher.generateRoute(request);
			
			rideNotifier.setRide(request.getRide(), "generateRoute");
			
			//records route sequence
			ride = rideDAO.createRoute(request.getRide());
			request.setRide(ride);
			
			//updates ride details 
			ride = rideDAO.updateRideDetails(request.getRide());
			request.setRide(ride);
			
			rideState.setState(new CompletingState(rideState));
		}else {
			rideState.setState(new WaitingState(rideState));
		}
		
		if(request.getRideType().equalsIgnoreCase("future")){
			rideState.setState(new WaitingState(rideState));
		}
		
		return request;
	}

	/**
	 * This method notifies to process ride before completing
	 * @param request
	 * @return Request
	 */
	@Override
	public Request completeRequest(Request request) {
		rideNotifier.setRide(request.getRide(), "ProcessRide");
		return request;
	}

}
