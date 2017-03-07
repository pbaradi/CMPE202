package com.cmpe202.g62.route;

import java.util.List;

import com.cmpe202.g62.model.Request;
import com.cmpe202.g62.model.Route;

/**
 * This interface is the Strategy interface of strategy pattern and is used for routing
 *
 */
public interface RoutingStrategy {
	
	/**
	 * This method generates route
	 * @param request
	 * @return List
	 */
	public List<Route> route(Request request);
}
