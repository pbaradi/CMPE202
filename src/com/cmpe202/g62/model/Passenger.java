package com.cmpe202.g62.model;

public class Passenger extends Member {
	
	private Payment payment;
	
	public Passenger(){
		super();
		setMemberType("Passenger");
	}
	
	public Passenger(int memberId, String userName, String firstName, String lastName, String password, String email,
			int phoneNumber, String memberType, String message, Location currentLocation, Payment payment) {
		super(memberId, userName, firstName, lastName, password, email, phoneNumber, memberType, message, currentLocation);
		// TODO Auto-generated constructor stub
		this.payment = payment;
		setMemberType("Passenger");
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	

}
