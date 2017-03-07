package com.cmpe202.g62.model;

import java.util.List;

import com.cmpe202.g62.notification.Notifier;

public class Ride extends Notifier{

	private int rideId;
	private Member driver;
	private List<Route> route;
	private Location parking;
	private Vehicle vehicle;
	private String date;
	private String time;
	private List<RideDetails> rideDetailsList;
	
	
	public Ride() {
		super();
	}
	

	public Ride(int rideId, Member driver, List<Route> route, Location parking, Vehicle vehicle, String date,
			String time, List<RideDetails> rideDetailsList) {
		super();
		this.rideId = rideId;
		this.driver = driver;
		this.route = route;
		this.parking = parking;
		this.vehicle = vehicle;
		this.date = date;
		this.time = time;
		this.rideDetailsList = rideDetailsList;
	}


	public int getRideId() {
		return rideId;
	}


	public void setRideId(int rideId) {
		this.rideId = rideId;
	}


	public Member getDriver() {
		return driver;
	}


	public void setDriver(Member driver) {
		this.driver = driver;
	}


	public List<Route> getRoute() {
		return route;
	}


	public void setRoute(List<Route> route) {
		this.route = route;
	}


	public Location getParking() {
		return parking;
	}


	public void setParking(Location parking) {
		this.parking = parking;
	}


	public Vehicle getVehicle() {
		return vehicle;
	}


	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public List<RideDetails> getRideDetailsList() {
		return rideDetailsList;
	}


	public void setRideDetailsList(List<RideDetails> rideDetailsList) {
		this.rideDetailsList = rideDetailsList;
	}
	
}
