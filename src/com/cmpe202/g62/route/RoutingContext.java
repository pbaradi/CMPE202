package com.cmpe202.g62.route;

import java.util.List;

import com.cmpe202.g62.model.Request;
import com.cmpe202.g62.model.Route;
import com.cmpe202.g62.route.impl.DistanceRouting;
import com.cmpe202.g62.route.impl.FCFSRouting;

/**
 * This class is context class of Strategy pattern and is used for routing
 *
 */
public class RoutingContext {

	private RoutingStrategy routingStrategy;

	/**
	 * This method generates route
	 * @param request
	 * @return List
	 */
	public List<Route> route(Request request){
		routingStrategy = setRoutingStrategy(request.getRouteStrategy());
		List<Route> route = routingStrategy.route(request);
		return route;
	}

	/**
	 * This method sets the routing stragtegy
	 * @param strategy
	 * @return RoutingStrategy
	 */
	public RoutingStrategy setRoutingStrategy(String strategy){
		switch(strategy){
		case "fcfs": 
			routingStrategy = new FCFSRouting();
			break;
		case "distance": 
			routingStrategy = new DistanceRouting();
			break;
		default : break;
		}
		return routingStrategy;
	}

}
