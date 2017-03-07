package com.cmpe202.g62.ride;

import com.cmpe202.g62.model.Request;
import com.cmpe202.g62.ride.impl.WaitingState;

/**
 * Context class for State Pattern
 * This class manages the ride
 *
 */
public class RideController implements RideInterface{
	
	private State state;
	
	public RideController() {
		this.state = new WaitingState(this);
	}
	
	/**
	 * This method receives the ride request
	 */
	@Override
	public Request receiveRideRequest(Request request) {
		request = state.receiveRequest(request);
		return request;
	}

	/**
	 * THis method processes the ride request
	 */
	@Override
	public Request processRideRequest(Request request) {
		request = state.processRequest(request);
		return request;
	}

	/**
	 * This method completes the ride request
	 */
	@Override
	public Request completeRideRequest(Request request) {
		request = state.completeRequest(request);
		return request;
	}

	/**
	 * This method gets the state
	 */
	@Override
	public State getState() {
		return state;
	}

	/**
	 * This method sets the state
	 */
	@Override
	public void setState(State s) {
		this.state = s;
	}

}
