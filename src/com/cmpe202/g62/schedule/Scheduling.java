package com.cmpe202.g62.schedule;

import com.cmpe202.g62.model.Request;

/**
 * This class is implementor of bridge pattern and is used for scheduling
 */
public abstract class Scheduling {
	
	/**
	 * This method schedules vehicle
	 * @param request
	 * @return Request
	 */
	public abstract Request scheduleVehicle(Request request);
	
	/**
	 * This method schedules parking
	 * @param request
	 * @return
	 */
	public abstract Request schedulePark(Request request);
	

}
