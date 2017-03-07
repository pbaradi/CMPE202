package com.cmpe202.g62.model;

import com.cmpe202.g62.notification.Notifier;

public class Request extends Notifier{
	
	private String parkingStrategy;
	private String vehicleStrategy;
	private Ride ride;
	private int noOfRides;
	private String rideType;
	private boolean confirmRide;
	private String routeStrategy;
	
	public String getParkingStrategy() {
		return parkingStrategy;
	}
	public void setParkingStrategy(String parkingStrategy) {
		this.parkingStrategy = parkingStrategy;
	}
	public String getVehicleStrategy() {
		return vehicleStrategy;
	}
	public void setVehicleStrategy(String vehicleStrategy) {
		this.vehicleStrategy = vehicleStrategy;
	}
	public Ride getRide() {
		return ride;
	}
	public void setRide(Ride ride) {
		this.ride = ride;
	}
	public boolean isConfirmRide() {
		return confirmRide;
	}
	public void setConfirmRide(boolean confirmRide) {
		this.confirmRide = confirmRide;
	}
	public int getNoOfRides() {
		return noOfRides;
	}
	public void setNoOfRides(int noOfRides) {
		this.noOfRides = noOfRides;
	}
	public String getRideType() {
		return rideType;
	}
	public void setRideType(String rideType) {
		this.rideType = rideType;
	}
	public String getRouteStrategy() {
		return routeStrategy;
	}
	public void setRouteStrategy(String routeStrategy) {
		this.routeStrategy = routeStrategy;
	}
}
