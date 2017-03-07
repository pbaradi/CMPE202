package com.cmpe202.g62.model;

public class Vehicle {
	private int vehicleId;
	private String vehicleType;
	private String vehicleName;
	private Member owner;
	private boolean available;
	
	public Vehicle(int vehicleId, String vehicleType, String vehicleName, Member owner, boolean available) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleType = vehicleType;
		this.vehicleName = vehicleName;
		this.owner = owner;
		this.available = available;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public Member getOwner() {
		return owner;
	}

	public void setOwner(Member owner) {
		this.owner = owner;
	}
	
	public String toString(){
		return this.vehicleType +" "+this.vehicleName;
	}
}
