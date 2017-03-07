package com.cmpe202.g62.route.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.cmpe202.g62.model.Location;
import com.cmpe202.g62.model.Request;
import com.cmpe202.g62.model.RideDetails;
import com.cmpe202.g62.model.Route;
import com.cmpe202.g62.route.RoutingStrategy;
import com.cmpe202.g62.util.CommonUtils;

/**
 * This class is concrete implementation of strategy interface of strategy pattern
 * it is used for routing
 *
 */
public class DistanceRouting implements RoutingStrategy{


	/**
	 * This method generates route by calculating distance between the locations
	 * @param request
	 * @return List
	 */
	public List<Route> route(Request request){
		List<Route> routeList = new ArrayList<>();
		List<RideDetails> addressList = request.getRide().getRideDetailsList();
		List<Location> sourceLocation = new ArrayList<>();
		List<Location> destLocation = new ArrayList<>();

		if(addressList.size()>1){
			for (RideDetails address : addressList) {
				sourceLocation.add(address.getSource());
				destLocation.add(address.getDestination());
			}

			//generates route for source locations
			List<Integer> sourceRoute = generateRoute(createMatrix(sourceLocation));
			for (int i = 0; i < sourceRoute.size(); i++) {
				routeList.add(new Route(0, sourceLocation.get(i), i+1));
			}

			//generates route for destination locations
			List<Integer> destRoute = generateRoute(createMatrix(destLocation));
			for (int i = 0; i < destRoute.size(); i++) {
				routeList.add(new Route(0, destLocation.get(i), i+1));
			}
		}else{
			routeList.add(new Route(0, addressList.get(0).getSource(), 1));
			routeList.add(new Route(0, addressList.get(0).getDestination(), 1));
		}
		return routeList;
	}

	/**
	 * This method generates the sequence of locations for a route
	 * @param mat
	 * @return List
	 */
	public List<Integer> generateRoute(double mat[][]){
		int numNode;
		Stack<Integer> stack = new Stack<Integer>();
		List<Integer> route = new ArrayList<>();
		numNode = mat[1].length - 1;
		int[] visited = new int[numNode + 1];
		visited[1] = 1;
		stack.push(1);
		int element, dst = 0, i;
		double min = Double.MAX_VALUE;
		boolean minFlag = false;
		route.add(1);

		while (!stack.isEmpty()){
			element = stack.peek();
			i = 1;
			min = Integer.MAX_VALUE;
			while (i <= numNode){
				if (mat[element][i] >= 1 && visited[i] == 0){
					if (min > mat[element][i]){
						min = mat[element][i];
						dst = i;
						minFlag = true;
					}
				}
				i++;
			}
			if (minFlag){
				visited[dst] = 1;
				stack.push(dst);
				route.add(dst);
				minFlag = false;
				continue;
			}
			stack.pop();
		}
		return route;
	}

	/**
	 * This method generates a matrix with distances between available locations
	 * @param locations
	 * @return
	 */
	private double[][] createMatrix(List<Location> locations){
		double mat[][] = new double[locations.size()][locations.size()];
		double distance;
		for (int i = 0; i < locations.size(); i++) {
			for(int j=0; j < locations.size(); j++){
				if(i!=j){
					distance = CommonUtils.getDistance(locations.get(i), locations.get(j));
					mat[i][j] = distance;
					mat[j][i] = distance;
				}
			}
		}

		return mat;
	}


}
