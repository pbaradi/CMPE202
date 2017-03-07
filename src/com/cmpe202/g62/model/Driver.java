package com.cmpe202.g62.model;

public class Driver extends Member {
	
	private Vehicle vehicle;
	
	public Driver(){
		super();
		this.setMemberType("Driver");
	}

	public Driver(int memberId, String userName, String firstName, String lastName, String password, String email,
			int phoneNumber, String memberType, String message, Location currentLocation, Vehicle vehicle) {
		super(memberId, userName, firstName, lastName, password, email, phoneNumber, memberType, message, currentLocation);
		// TODO Auto-generated constructor stub
		this.vehicle = vehicle;
		this.setMemberType("Driver");
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
