package com.cmpe202.g62.schedule;

import com.cmpe202.g62.model.Request;


/**
 * This interface is strategy of strategy pattern
 * @author pavanibaradi
 *
 */
public interface ScheduleStrategy {
	
	
	/**
	 * This method schedules vehicle
	 * @param request
	 * @return Request
	 */
	public Request scheduleVehicle(Request request);
	
	/**
	 * This method schedules parking
	 * @param request
	 * @return Request
	 */
	public Request scheduleParking(Request request);

}
