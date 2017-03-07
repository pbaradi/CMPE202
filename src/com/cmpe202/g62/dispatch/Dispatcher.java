package com.cmpe202.g62.dispatch;

import java.util.ArrayList;
import java.util.List;

import com.cmpe202.g62.model.Request;
import com.cmpe202.g62.model.Ride;
import com.cmpe202.g62.model.RideDetails;
import com.cmpe202.g62.model.Route;
import com.cmpe202.g62.route.RoutingContext;
import com.cmpe202.g62.util.CommonUtils;

/**
 * This class is client for routing stategy pattern
 *
 */
public class Dispatcher {

	/**
	 * This method generates route
	 * @param request
	 * @return Request
	 */
	public Request generateRoute(Request request){
		Ride ride = request.getRide();
		RoutingContext rc = new RoutingContext();
		List<Route> route = rc.route(request);
		ride.setRoute(route);
		request.setRide(ride);
		return request;
	}

	/**
	 * This method calculates the ride amount
	 * @param request
	 * @return Request
	 */
	public Request calculateRideAmount(Request request) {
		List<RideDetails> addressList = request.getRide().getRideDetailsList();
		List<RideDetails> newList = new ArrayList<>();
		double amount;
		for (RideDetails address : addressList) {
			amount = CommonUtils.getDistance(address.getSource(), address.getDestination())*2;
			address.setRideAmount(amount);
			newList.add(address);
		}
		request.getRide().setRideDetailsList(newList);
		return request;
	}

	/**
	 * This method calculates park amount
	 * @param request
	 * @return
	 */
	public Request calculateParkAmount(Request request) {
		List<RideDetails> addressList = request.getRide().getRideDetailsList();
		List<RideDetails> newList = new ArrayList<>();
		double amount = 5;
		for (RideDetails address : addressList) {
			address.setParkingAmount(amount);
			newList.add(address);
		}
		request.getRide().setRideDetailsList(newList);
		return request;
	}

}
