package com.cmpe202.g62.schedule.impl;

import com.cmpe202.g62.model.Request;
import com.cmpe202.g62.ride.impl.RideScheduler;
import com.cmpe202.g62.schedule.Scheduling;

/**
 * This class is refined abstraction of bridge pattern used for scheduling vehicle 
 *
 */
public class VehicleScheduler extends RideScheduler {
	

	public VehicleScheduler(Scheduling s) {
		super(s);
	}

	/**
	 * This method schedules vehicle
	 * @param request
	 * @return Request
	 */
	@Override
	public Request schedule(Request request) {
		request = scheduleVehicle(request);
		return request;
	}

}
