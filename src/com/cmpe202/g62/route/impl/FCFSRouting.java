package com.cmpe202.g62.route.impl;

import java.util.ArrayList;
import java.util.List;

import com.cmpe202.g62.model.Request;
import com.cmpe202.g62.model.RideDetails;
import com.cmpe202.g62.model.Route;
import com.cmpe202.g62.route.RoutingStrategy;

public class FCFSRouting implements RoutingStrategy{

	/**
	 * This method generates route by first come first serve 
	 * @param request
	 * @return List
	 */
	@Override
	public List<Route> route(Request request) {
		int i =0;
		List<Route> routeList = new ArrayList<>();
		List<RideDetails> addressList = request.getRide().getRideDetailsList();
		
		for (RideDetails address : addressList) {
			routeList.add(new Route(0, address.getSource(), ++i));
		}
		
		for (RideDetails address : addressList) {
			routeList.add(new Route(0, address.getDestination(), ++i));
		}
		
		return routeList;
	}

}
