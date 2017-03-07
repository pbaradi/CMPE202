package com.cmpe202.g62.model;

public class Location {
	private int locationId;
	private int latitude;
	private int longitude;
	private boolean parking;
	
	public Location(int locationId) {
		super();
		this.locationId = locationId;
	}
	public Location(int latitude, int longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Location(int locationId, int latitude, int longitude) {
		super();
		this.locationId = locationId;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Location(int locationId, int latitude, int longitude, boolean parking) {
		super();
		this.locationId = locationId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.parking = parking;
	}
	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	
	public boolean isParking() {
		return parking;
	}
	
	public void setParking(boolean parking) {
		this.parking = parking;
	}
	
	public String toString(){
		return "Location "+this.latitude + ", "+this.longitude;
	}

}
