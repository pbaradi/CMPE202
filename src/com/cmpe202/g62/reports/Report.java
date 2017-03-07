package com.cmpe202.g62.reports;

import com.cmpe202.g62.model.Location;

/**
 * This class is leaf of composite pattern used for reporting
 *
 */
public class Report extends ReportComponent {

	private String firstName;
	private String lastName;
	private Location source;
	private Location destination;
	private double rideAmount;
	private double parkAmount;

	

	public Report(String firstName, String lastName, Location source, Location destination, double rideAmount,
			double parkAmount) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.source = source;
		this.destination = destination;
		this.rideAmount = rideAmount;
		this.parkAmount = parkAmount;
	}

	public Report(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Location getSource() {
		return source;
	}

	public void setSource(Location source) {
		this.source = source;
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

	public double getRideAmount() {
		return rideAmount;
	}

	public void setRideAmount(double rideAmount) {
		this.rideAmount = rideAmount;
	}

	public double getParkAmount() {
		return parkAmount;
	}

	public void setParkAmount(double parkAmount) {
		this.parkAmount = parkAmount;
	}

	/**
	 * This method displays the report object
	 */
	public void displayReport() {
		StringBuffer sb = new StringBuffer();
		sb.append(getFirstName()+" "+getLastName());
		if(getSource()!=null){
			sb.append(" rode from "+getSource().toString()+" to "+getDestination().toString()+" which costed "+getRideAmount()+getParkAmount());
		}
		System.out.println(sb.toString());
	}


}
