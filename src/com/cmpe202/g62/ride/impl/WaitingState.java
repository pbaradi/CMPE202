package com.cmpe202.g62.ride.impl;

import com.cmpe202.g62.dao.RideDAO;
import com.cmpe202.g62.dispatch.Dispatcher;
import com.cmpe202.g62.model.Request;
import com.cmpe202.g62.model.Ride;
import com.cmpe202.g62.notification.RideNotifier;
import com.cmpe202.g62.notification.RideObserver;
import com.cmpe202.g62.ride.RideInterface;
import com.cmpe202.g62.ride.State;
import com.cmpe202.g62.schedule.Scheduling;
import com.cmpe202.g62.schedule.impl.FutureScheduling;
import com.cmpe202.g62.schedule.impl.ImmediateScheduling;
import com.cmpe202.g62.schedule.impl.ParkingScheduler;
import com.cmpe202.g62.schedule.impl.VehicleScheduler;

/**
 * This class is one of the concrete states of State pattern
 *
 */
public class WaitingState implements State{

	RideInterface rideState;
	RideNotifier rideNotifier;
	RideDAO rideDAO;

	/**
	 * Constructor initializes the objects  
	 * @param request
	 */
	public WaitingState(RideInterface rideState){
		this.rideState = rideState;
		rideState.setState(this);
		
		rideNotifier = new RideNotifier();
		RideObserver rideObserver = new RideObserver();
		rideNotifier.addObserver(rideObserver);
		
		rideDAO = new RideDAO();
	}

	/**
	 * This method receives request, schedules vehicle and parking, calculates ride fare
	 * @param request
	 * @return Request
	 */
	@Override
	public Request receiveRequest(Request request) {
		
		Scheduling scheduler;
		RideScheduler rideScheduler;
		Dispatcher dispatcher = new Dispatcher();
		
		//notifies that ride requested is confirmed
		rideNotifier.setRide(request.getRide(), "RequestingRide");
		
		//initializes scheduler object based on the schedule strategy
		if(request.getRideType().equals("now")){
			scheduler = new ImmediateScheduling();
			request.setRouteStrategy("distance");
			request.setVehicleStrategy("nearest");
			request.setParkingStrategy("nearest");
		}else{
			scheduler = new FutureScheduling();
			request.setRouteStrategy("fcfs");
			request.setVehicleStrategy("nearest");
			request.setParkingStrategy("nearest");
		}
		
		//schedules vehicle
		rideScheduler = new VehicleScheduler(scheduler);
		request = rideScheduler.schedule(request);

		//schedules parking
		rideScheduler = new ParkingScheduler(scheduler);
		request = rideScheduler.schedule(request);
		
		//records ride details 
		Ride ride = rideDAO.createRide(request.getRide());
		request.setRide(ride);
		
		rideNotifier.setRide(request.getRide(), "scheduleRide");
		
		//calculates ride and parking fare
		request = dispatcher.calculateRideAmount(request);
		if(request.getRide().getParking()!=null){
			request = dispatcher.calculateParkAmount(request);
		}
		
		//notifies member
		rideNotifier.setRide(request.getRide(), "calculateAmount");
		
		if(request.getRideType().equals("now")){
			rideState.setState(new StartingState(rideState));
		}else{
			rideState.setState(new WaitingState(rideState));
		}
		
		
		return request;
	}

	/**
	 * This method notifies to request ride before processing
	 * @param request
	 * @return Request
	 */
	@Override
	public Request processRequest(Request request) {
		rideNotifier.setRide(request.getRide(), "requestRide");
		return request;
	}

	/**
	 * This method notifies to request ride before completing
	 * @param request
	 * @return Request
	 */
	@Override
	public Request completeRequest(Request request) {
		rideNotifier.setRide(request.getRide(), "requestRide");
		return request;
	}

}
