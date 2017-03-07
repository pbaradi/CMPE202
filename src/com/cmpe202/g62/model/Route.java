package com.cmpe202.g62.model;

public class Route {
	
	private int route_id;
	private Location location;
	private int seq;
	
	public Route(){
	}
	

	public Route(int route_id, Location location, int seq) {
		super();
		this.route_id = route_id;
		this.location = location;
		this.seq = seq;
	}


	public int getRoute_id() {
		return route_id;
	}

	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}


	public Location getLocation() {
		return location;
	}


	public void setLocation(Location location) {
		this.location = location;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}

	

}
