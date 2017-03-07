package com.cmpe202.g62.schedule.alg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cmpe202.g62.dao.ParkingDAO;
import com.cmpe202.g62.dao.VehicleDAO;
import com.cmpe202.g62.model.Location;
import com.cmpe202.g62.model.Request;
import com.cmpe202.g62.model.Vehicle;
import com.cmpe202.g62.schedule.ScheduleStrategy;
import com.cmpe202.g62.util.CommonUtils;

public class RoundRobinSchedule implements ScheduleStrategy{

	@Override
	public Request scheduleVehicle(Request request) {
		VehicleDAO vehicleDAO = new VehicleDAO();

		//fetches available vehicles to schedule
		List<Vehicle> vehicleList = vehicleDAO.getAvailableVehicles();
		Location memberLocation = new Location(1,0,0);
		Map<Vehicle, Double> vehicleMap = new HashMap<Vehicle, Double>();

		//finds the distance between available vehicles
		for (Vehicle vehicle : vehicleList) {
			double distance = CommonUtils.getDistance(memberLocation, vehicle.getOwner().getCurrentLocation());
			vehicleMap.put(vehicle, distance);
		}

		//sorts the vehicles by distance
		vehicleMap = CommonUtils.sortByValue(vehicleMap);
		Vehicle vehicle = vehicleMap.keySet().iterator().next();
		request.getRide().setDriver(vehicle.getOwner());
		request.getRide().setVehicle(vehicle);
		return request;
	}

	@Override
	public Request scheduleParking(Request request) {
		Location destLocation;
		ParkingDAO parkingDAO = new ParkingDAO();

		//fetches available parking
		List<Location> locationList = parkingDAO.getAvailableParking();
		Map<Location,Double> locationMap = new HashMap<Location,Double>();
		int size = request.getRide().getRideDetailsList().size();
		if(size>0){
			destLocation = request.getRide().getRideDetailsList().get(size-1).getDestination();
		}else{
			destLocation = new Location(1,0,0);
		}

		//finds the distance between available parkings
		for (Location location : locationList) {
			double distance = CommonUtils.getDistance(destLocation, location);
			locationMap.put(location, distance);
		}

		//sorts the parking be distance
		locationMap = CommonUtils.sortByValue(locationMap);
		Location location = locationMap.keySet().iterator().next();
		request.getRide().setParking(location);
		return request;
	}

}
