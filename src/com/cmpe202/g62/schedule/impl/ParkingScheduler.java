package com.cmpe202.g62.schedule.impl;

import com.cmpe202.g62.model.Request;
import com.cmpe202.g62.ride.impl.RideScheduler;
import com.cmpe202.g62.schedule.Scheduling;

/**
 * This class is refined abstraction of bridge pattern used for scheduling parking
 *
 */
public class ParkingScheduler extends RideScheduler {
	

	public ParkingScheduler(Scheduling s) {
		super(s);
	}

	/**
	 * This method schedules parking
	 * @param request
	 * @return Request
	 */
	@Override
	public Request schedule(Request request) {
		request = schedulePark(request);
		return request;
	}

}
