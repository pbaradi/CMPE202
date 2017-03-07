package com.cmpe202.g62.model;

public class PremiumMember extends Member{
	private String premiumCategory;

	public String getPremiumCategory() {
		return premiumCategory;
	}

	public void setPremiumCategory(String premiumCategory) {
		this.premiumCategory = premiumCategory;
	}

	public PremiumMember(String premiumCategory) {
		super();
		this.premiumCategory = premiumCategory;
	}
	
	public PremiumMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PremiumMember(int memberId, String userName, String firstName, String lastName, String password,
			String email, int phoneNumber, String memberType, String message, Location currentLocation) {
		super(memberId, userName, firstName, lastName, password, email, phoneNumber, memberType, message,
				currentLocation);
	}

	public PremiumMember(int memberId, String userName, String firstName, String lastName, String password,
			String email, int phoneNumber, String memberType, String message, Location currentLocation,
			String premiumCategory) {
		super(memberId, userName, firstName, lastName, password, email, phoneNumber, memberType, message,
				currentLocation);
		this.premiumCategory = premiumCategory;
	}
	
}
