package com.cmpe202.g62.schedule.impl;

import com.cmpe202.g62.model.Request;
import com.cmpe202.g62.schedule.ScheduleStrategy;
import com.cmpe202.g62.schedule.alg.NearestLocationSchedule;

/**
 * This class is context class of strategy pattern and is used for scheduling
 *
 */
public class ScheduleContext {

	private ScheduleStrategy ss;

	/**
	 * This method schedules vehicle
	 * @param request
	 * @return Request
	 */
	public Request scheduleVehicle(Request request){
		ss = setScheduleStrategy(request.getVehicleStrategy());
		request = ss.scheduleVehicle(request);
		return request;
	}
	
	/**
	 * This method schedules parking
	 * @param request
	 * @return Request
	 */
	public Request scheduleParking(Request request){
		ss = setScheduleStrategy(request.getParkingStrategy());
		request = ss.scheduleParking(request);
		return request;
	}

	/**
	 * This method sets schedule strategy 
	 * @param strategy
	 * @return ScheduleStrategy
	 */
	private ScheduleStrategy setScheduleStrategy(String strategy){
		switch(strategy){
		case "nearest": 
			ss = new NearestLocationSchedule();
			break;
		default :
			ss = new NearestLocationSchedule();
			break;
		}
		return ss;
	}

}
