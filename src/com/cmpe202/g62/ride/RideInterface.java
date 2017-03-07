package com.cmpe202.g62.ride;

import com.cmpe202.g62.model.Request;

public interface RideInterface {
	
	/**
	 * This method receives the ride request
	 */
	public Request receiveRideRequest(Request request);
	
	/**
	 * THis method processes the ride request
	 */
	public Request processRideRequest(Request request);
	
	/**
	 * This method completes the ride request
	 */
	public Request completeRideRequest(Request request);
	
	/**
	 * This method gets the state
	 */
	public State getState();
	
	/**
	 * This method sets the state
	 */
	public void setState(State s);

}
