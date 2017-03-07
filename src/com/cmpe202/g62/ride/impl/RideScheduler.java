package com.cmpe202.g62.ride.impl;

import com.cmpe202.g62.model.Request;
import com.cmpe202.g62.schedule.Scheduling;

/**
 * This class is abstraction of bridge pattern
 *
 */
public abstract class RideScheduler 
{
	protected Scheduling scheduling;
	
	/**
	 * This method schedules vehicle or parking
	 * @param request
	 * @return
	 */
	public abstract Request schedule(Request request);
	
	public RideScheduler(Scheduling s){
		scheduling = s;
	}

	/**
	 * This method schedules vehicle
	 * @param request
	 * @return Request
	 */
	protected Request scheduleVehicle(Request request){
		return scheduling.scheduleVehicle(request);
	}
	
	/**
	 * This method schedules parking
	 * @param request
	 * @return Request
	 */
	protected Request schedulePark(Request request){
		return scheduling.schedulePark(request);
	}

}
