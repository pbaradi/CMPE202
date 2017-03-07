package com.cmpe202.g62.schedule.impl;

import com.cmpe202.g62.model.Request;
import com.cmpe202.g62.schedule.Scheduling;

/**
 * This class is concrete implementor of bridge pattern
 * and is used for scheduling immediate requests
 */
public class ImmediateScheduling extends Scheduling {
	
	private ScheduleContext sc;

	/**
	 * This method schedules vehicle
	 * @param request
	 * @return Request
	 */
	@Override
	public Request scheduleVehicle(Request request) {
		sc = new ScheduleContext();
		return sc.scheduleVehicle(request);
	}

	/**
	 * This method schedules parking
	 * @param request
	 * @return
	 */
	@Override
	public Request schedulePark(Request request) {
		sc = new ScheduleContext();
		return sc.scheduleParking(request);
	}

}
