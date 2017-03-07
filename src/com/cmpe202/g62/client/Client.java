package com.cmpe202.g62.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cmpe202.g62.model.CardDetails;
import com.cmpe202.g62.model.Driver;
import com.cmpe202.g62.model.Location;
import com.cmpe202.g62.model.Member;
import com.cmpe202.g62.model.Passenger;
import com.cmpe202.g62.model.PayPal;
import com.cmpe202.g62.model.Payment;
import com.cmpe202.g62.model.Request;
import com.cmpe202.g62.model.Ride;
import com.cmpe202.g62.model.RideDetails;
import com.cmpe202.g62.model.Vehicle;
import com.cmpe202.g62.request.RequestController;

public class Client {

	private static RequestController request = new RequestController();
	static Scanner scan;
	private static Member member;
	private static Request r;


	/**
	 * Client main method
	 * This method starts the application
	 * @param args
	 */
	public static void main(String[] args) {
		r = new Request();
		scan = new Scanner(System.in);
		System.out.println("========================== Welcome to shareAride Application =======================");
		boolean exited = false;
		while(!exited){
			System.out.println("\n Please select one of the following");
			System.out.println("1. Member Operations");
			System.out.println("2. Request ride");
			System.out.println("3. Reports");
			System.out.println("4. Report an issue");
			System.out.println("5. Exit");
			String choice = scan.nextLine();
			if (choice.equals("")) {
				break;
			}
			switch (Integer.parseInt(choice)) {	
			case 1:
				memberOperations();
				break;
			case 2:
				requestRide();
				break;
			case 3:
				reports();
				break;
			case 4:
				notifyMember();
				break;
			case 5:
				exited = true;
				break;
			default:
				break;			
			}
		}
		scan.close();
	}

	/**
	 * Member Operations
	 * This method lists the operations member can perform
	 */
	private static void memberOperations(){
		System.out.println("\n Please select one of the following. Please enter blank to go back to previous menu.");
		System.out.println("1. Register");
		System.out.println("2. SignIn");
		System.out.println("3. Update");
		System.out.println("4. Delete");
		String choice = scan.nextLine();
		switch (Integer.parseInt(choice)) {	
		case 1:
			registerMember();
			break;
		case 2:
			signIn();
			break;
		case 3:
			updateMember();
			break;
		case 4:
			deleteMember();
			break;
		default:	
			break;			
		}
	}


	/**
	 * This method lists the options to register member
	 * as driver or passenger
	 */
	private static void registerMember(){
		System.out.println("\n Please select one of the following. Please enter blank to go back to previous menu.");
		System.out.println("1. Register as Driver");
		System.out.println("2. Register as Passenger");
		String choice = scan.nextLine();

		switch (Integer.parseInt(choice)) {
		case 1:
			member = new Driver();
			break;
		case 2:
			member = new Passenger();
			break;
		default:	
			break;			
		}
		memberDetails();
	}

	/**
	 * This method prompts for member details for registering
	 */
	private static void memberDetails(){
		String premiumCategory = "REGULAR";
		System.out.println("\n Please enter the details for membership");
		System.out.println("First Name :");
		String firstName = scan.nextLine();
		System.out.println("Last Name");
		String lastName = scan.nextLine();
		System.out.println("Username :");
		String userName = scan.nextLine();
		System.out.println("Password");
		String password = scan.nextLine();
		System.out.println("Email :");
		String email = scan.nextLine();
		System.out.println("Phone Number :");
		String phoneNumber = scan.nextLine();

		if(member.getMemberType().equals("Driver")){
			member = new Driver(0, userName, firstName, lastName, password, email, Integer.parseInt(phoneNumber), "Driver", null, null, null);
			Vehicle vehicle = vehicleDetails();
			member = request.registerMember(member);
			if(member!=null){
				vehicle.setOwner(member);
				vehicle = request.registerVehicle(vehicle);
				((Driver)member).setVehicle(vehicle);
			}
		}
		else{
			member = new Passenger(0, userName, firstName, lastName, password, email, Integer.parseInt(phoneNumber), "Passenger", null, null, null);
			Payment payment = paymentDetails();
			premiumCategory = premiumDetails();
			member = request.registerMember(member);
			if(member!=null){
				payment = request.addPaymentDetails(payment);
				((Passenger)member).setPayment(payment);
			}
		}
		member = request.updateMembership(member, premiumCategory);
	}

	/**
	 * This method prompts for driver's vehicle details while registering driver
	 * @return Vehicle
	 */
	private static Vehicle vehicleDetails(){
		System.out.println("\n Please enter your vehicle details");
		System.out.println("Vehicle Type :");
		String vehicleType = scan.nextLine();
		System.out.println("Vehicle ID");
		String vehicleName = scan.nextLine();
		Vehicle vehicle = new Vehicle(0, vehicleType, vehicleName, member, true);
		return vehicle;
	}

	/**
	 * This method lists payment options while registering passenger
	 * Passenger's payment details will be registered while registering a passenger.
	 * @return Payment
	 */
	private static Payment paymentDetails(){
		Payment payment;
		System.out.println("\n Please enter your payment details");
		System.out.println("1. Add Paypal Account");
		System.out.println("2. Add Credit Card");
		String choice = scan.nextLine();
		switch(Integer.parseInt(choice)){
		case 1: 
			payment = addPaypal();
			break;
		case 2: 
			payment = addCreditCard();
			break;
		default:
			payment = addCreditCard();
		}
		return payment;
	}

	/**
	 * This method prompts for Paypal details of Passenger
	 * @return Payment
	 */
	private static Payment addPaypal(){
		System.out.println("Paypal email Id :");
		String emailId = scan.nextLine();
		System.out.println("Paypal password :");
		String password = scan.nextLine();
		System.out.println("Card Valid Date in YYYY-MM-DD format");
		String validDate = scan.nextLine();
		Payment payment = new PayPal(0, java.sql.Date.valueOf(validDate), "Paypal", member, emailId, password);
		return payment;
	}

	/**
	 * This method prompts for Credit card details of passenger
	 * @return Payment
	 */
	private static Payment addCreditCard(){
		System.out.println("Card Number :");
		String cardNumber = scan.nextLine();
		System.out.println("Card Valid Date in YYYY-MM-DD format");
		String validDate = scan.nextLine();
		Payment payment = new CardDetails(0, java.sql.Date.valueOf(validDate), "creditCard", member, Long.parseLong(cardNumber));
		return payment;
	}

	/**
	 * This method lists membership options to upgrade
	 * @return premiumType
	 */
	private static String premiumDetails(){
		System.out.println("Get the benefits we give by upgrading your account to premium. Would you like to be a premium member with shareARide?[y/n]");
		String premiumType = "REGULAR";
		switch(scan.nextLine()){
		case "y":
			System.out.println("There are two premium options we offer:");
			System.out.println("1. Gold");
			System.out.println("2. Silver");
			String choice = scan.nextLine();
			switch(Integer.parseInt(choice)){
			case 1:
				premiumType = "GOLD";
				break;
			case 2:
				premiumType = "SILVER";
				break;
			}
			break;
		case "n":
			premiumType = "REGULAR";
			break;
		}
		return premiumType;
	}

	/**
	 * This method prompts for user credentials for sign in
	 */
	private static void signIn(){
		System.out.println("\n Please enter sign in details");
		System.out.println("Username :");
		String userName = scan.nextLine();
		System.out.println("Password :");
		String password = scan.nextLine();
		member = request.signInMember(userName, password);
	}

	/**
	 * This method prompts member details to update
	 */
	private static void updateMember(){
		if(member!=null){
			System.out.println("First Name :");
			String firstName = scan.nextLine();
			System.out.println("Last Name");
			String lastName = scan.nextLine();
			System.out.println("Password");
			String password = scan.nextLine();
			System.out.println("Phone Number :");
			String phoneNumber = scan.nextLine();
			member.setFirstName(firstName);
			member.setLastName(lastName);
			member.setPassword(password);
			member.setPhoneNumber(Integer.parseInt(phoneNumber));
			member = request.updateMember(member);
		}else{
			System.out.println("Please sign in before updating the details.");
		}
	}

	/**
	 * This method soft deletes member 
	 */
	private static void deleteMember(){
		System.out.println("Username :");
		String userName = scan.nextLine();
		member = new Member();
		member.setUserName(userName);
		request.deleteMember(member);
	}


	/**
	 * This method lists ride request options 
	 */
	private static void requestRide(){
		if(member!=null){
			if(member.getMemberType().equalsIgnoreCase("passenger")){
			System.out.println("\n Please select a ride request option. Enter blank to exit");
			System.out.println("1. Request for now");
			System.out.println("2. Request for later");
			String choice = scan.nextLine();
			switch(choice){
			case "1":
				r.setRideType("now");
				r = scheduleRide(r);
				scheduleNow();
				break;
			case "2":
				r.setRideType("future");
				r = scheduleRide(r);
				break;
			default:
				break;
			}
			}else{
				System.out.println("Please sign in as passenger to request a ride");
			}
		}else{
			System.out.println("Please sign in before requesting a ride.");
		}
	}


	/**
	 * This method prompts for ride operations
	 * track ride
	 * pay for ride
	 */
	private static void scheduleNow() {
		while(true){
			System.out.println("\n Please select a ride request option. Enter blank to exit");
			System.out.println("1. Track the Vehicle");
			System.out.println("2. Pay for the ride");
			String choice = scan.nextLine();
			if(choice.equals("")){
				break;
			}
			switch(Integer.parseInt(choice)){
			case 1:
				trackVehicle(r);
				break;
			case 2:
				payment();
				break;
			default:
				break;
			}
		}
	}

	/**
	 * This method schedules the ride 
	 * @param Request
	 * @return Request
	 */
	private static Request scheduleRide(Request r){
		System.out.println("\n Please enter number of ride requests you want to make?");
		String noOfRides = scan.nextLine();
		List<RideDetails> rideDetailsList = new ArrayList<RideDetails>();
		Member passenger;
		Ride ride = new Ride();
		for(int i=0;i<Integer.parseInt(noOfRides);i++){
			System.out.println("\n Request ride as same user? Please enter y/n");
			String choice = scan.nextLine();
			if(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")){
				passenger = member;
			}else{
				System.out.println("\n Please enter sign in details");
				System.out.println("Username :");
				String userName = scan.nextLine();
				System.out.println("Password :");
				String password = scan.nextLine();
				passenger = request.signInMember(userName, password);
				if(passenger!=null)
					member = passenger;
			}
			if(passenger!=null){
				if(passenger.getMemberType().equalsIgnoreCase("passenger")){
					System.out.println("Enter source coordinates :");
					System.out.println("x-coordinate");
					String sourceLatitude = scan.nextLine();
					System.out.println("y-coordinate");
					String sourceLongitude = scan.nextLine();
					System.out.println("Enter destination coordinates :");
					System.out.println("x-coordinate");
					String destinationLatitude = scan.nextLine();
					System.out.println("y-coordinate");
					String destinationLongitude = scan.nextLine();
					if(r.getRideType().equalsIgnoreCase("future")){
						System.out.println("Please enter schedule date in in YYYY-MM-DD format");
						ride.setDate(scan.nextLine());
						System.out.println("Please enter schedule time");
						ride.setTime(scan.nextLine());
					}
					Location source = new Location(Integer.parseInt(sourceLatitude), Integer.parseInt(sourceLongitude));
					Location destination = new Location(Integer.parseInt(destinationLatitude), Integer.parseInt(destinationLongitude));
					rideDetailsList.add(new RideDetails(passenger, source, destination));
				}else{
					System.out.println("\n Please sign in as a passenger");
					break;
				}
			}else{
				System.out.println("\n Please enter valid username and password or register as new user");
			}
		}

		ride.setRideDetailsList(rideDetailsList);
		r.setRide(ride);

		r = request.scheduleRide(r);
		return r;
	}

	/**
	 * This method tracks the ride
	 * @param Request
	 * @return Request
	 */
	private static Request trackVehicle(Request r){
		r = request.processRide(r);
		return r;
	}

	/**
	 * This method prompts payment options
	 */
	private static void payment(){
		System.out.println("\n Please enter the mode of payment or blank to go back");
		System.out.println("1. Pay by credit card");
		System.out.println("2. Pay by paypal");
		String choice = scan.nextLine();
		switch(Integer.parseInt(choice)){
		case 1:
			creditCard();
			break;
		case 2:
			paypal();
			break;
		default:	
			break;
		}
		r = request.completeRide(r);
	}

	/**
	 * This method prompts for credit card details to pay for ride
	 */
	private static void creditCard(){
		Ride ride = r.getRide();
		List<RideDetails> newList = new ArrayList<>();
		Member m = null;
		String validDate = "2018-10-10";
		String cardNumber = "1234";

		for (RideDetails rideDetails : ride.getRideDetailsList()) {

			if(m == null || !(m.getFirstName().equals(rideDetails.getPassenger().getFirstName()) && m.getLastName().equals(rideDetails.getPassenger().getLastName()))){
				m = rideDetails.getPassenger();
				System.out.println("\n Dear "+ rideDetails.getPassenger().getFirstName() +" "+rideDetails.getPassenger().getLastName()+" please enter Credit card details");
				System.out.println("Card Number :");
				cardNumber = scan.nextLine();
				System.out.println("Card Valid Date");
				validDate = scan.nextLine();
			}
			Payment payment = new CardDetails(0, java.sql.Date.valueOf(validDate),"creditCard" ,rideDetails.getPassenger(), Long.parseLong(cardNumber));
			rideDetails.setPayment(payment);
			newList.add(rideDetails);
		}
		ride.setRideDetailsList(newList);
		r.setRide(ride);
	}

	/**
	 * This method prompts for Paypal details to pay for ride
	 */
	private static void paypal(){
		Ride ride = r.getRide();
		Member m = null;
		String validDate = "2018-10-10";
		String email=null;
		String password=null;
		List<RideDetails> newList = new ArrayList<>();
		for (RideDetails rideDetails : ride.getRideDetailsList()) {
			if(m == null || !(m.getFirstName().equals(rideDetails.getPassenger().getFirstName()) && m.getLastName().equals(rideDetails.getPassenger().getLastName()))){
				m = rideDetails.getPassenger();
				System.out.println("\n Please enter Paypal account details");
				System.out.println("Email id :");
				email = scan.nextLine();
				System.out.println("Password :");
				password = scan.nextLine();
				System.out.println("Valid Date");
				validDate = scan.nextLine();
			}
			Payment payment = new PayPal(0, java.sql.Date.valueOf(validDate), "paypal", rideDetails.getPassenger(), email, password);
			rideDetails.setPayment(payment);
			newList.add(rideDetails);
		}
		ride.setRideDetailsList(newList);
		r.setRide(ride);

	}

	/**
	 * This method lists report options
	 */
	private static void reports(){
		System.out.println("Please select the report type to be generated");
		System.out.println("1. Member Report");
		System.out.println("2. Passenger Ride Report");
		String choice = scan.nextLine();

		switch(Integer.parseInt(choice)){
		case 1:
			memberReport();
			break;
		case 2:
			passengerRideReport();
			break;
		default:	
			break;
		}
	}

	/**
	 * This method generates passenger ride report
	 */
	private static void passengerRideReport() {
		request.passengerRideReport();
	}

	/**
	 * This method generates member report
	 */
	private static void memberReport() {
		request.memberReport();	
	}

	/**
	 * This method notifies about any issue
	 */
	private static void notifyMember(){
		if(member!=null){
			System.out.println("\n Please enter the message to notify");
			String message = scan.nextLine();
			request.reportIssue(member, message);
		}else{
			System.out.println("Please sign in before reporting an issue");
		}
	}

}
