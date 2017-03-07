package com.cmpe202.g62.model;

public class RideDetails {
	
	private int rideDetailsId;
	private Member passenger;
	private Location source;
	private Location destination;
	private double rideAmount;
	private double parkingAmount;
	private Payment payment;
	
	public RideDetails(int rideDetailsId, Member passenger, Location source, Location destination, double rideAmount,
			double parkingAmount) {
		super();
		this.rideDetailsId = rideDetailsId;
		this.passenger = passenger;
		this.source = source;
		this.destination = destination;
		this.rideAmount = rideAmount;
		this.parkingAmount = parkingAmount;
	}

	public RideDetails(Member passenger, Location source, Location destination, double rideAmount, double parkingAmount) {
		super();
		this.passenger = passenger;
		this.source = source;
		this.destination = destination;
		this.rideAmount = rideAmount;
		this.parkingAmount = parkingAmount;
	}

	public RideDetails(int rideDetailsId, Member passenger, Location source, Location destination, double rideAmount,
			double parkingAmount, Payment payment) {
		super();
		this.rideDetailsId = rideDetailsId;
		this.passenger = passenger;
		this.source = source;
		this.destination = destination;
		this.rideAmount = rideAmount;
		this.parkingAmount = parkingAmount;
		this.payment = payment;
	}

	public RideDetails(Member passenger, Location source, Location destination) {
		super();
		this.passenger = passenger;
		this.source = source;
		this.destination = destination;
	}

	public RideDetails(Location source, Location destination) {
		super();
		this.source = source;
		this.destination = destination;
	}
	
	public int getRideDetailsId() {
		return rideDetailsId;
	}

	public void setRideDetailsId(int rideDetailsId) {
		this.rideDetailsId = rideDetailsId;
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

	public Member getPassenger() {
		return passenger;
	}

	public void setPassenger(Member passenger) {
		this.passenger = passenger;
	}

	public double getRideAmount() {
		return rideAmount;
	}

	public void setRideAmount(double rideAmount) {
		this.rideAmount = rideAmount;
	}

	public double getParkingAmount() {
		return parkingAmount;
	}

	public void setParkingAmount(double parkingAmount) {
		this.parkingAmount = parkingAmount;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
}
