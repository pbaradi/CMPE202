package com.cmpe202.g62.model;

public class Member{
	private int memberId;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private int phoneNumber;
	private String memberType;
	private String message;
	private Location currentLocation;

	public Member(){

	}
	
	public Member(int memberId){
		this.memberId = memberId;
	}

	public Member(int memberId, String userName, String firstName, String lastName, String password, String email,
			int phoneNumber, String memberType, String message, Location currentLocation) {
		super();
		this.memberId = memberId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.memberType = memberType;
		this.message = message;
		this.currentLocation = currentLocation;
	}

	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	public String toString(){
		return getFirstName()+" "+getLastName();
	}

}
