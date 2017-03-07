package com.cmpe202.g62.request;

import com.cmpe202.g62.dao.VehicleDAO;
import com.cmpe202.g62.member.MemberController;
import com.cmpe202.g62.model.Member;
import com.cmpe202.g62.model.Payment;
import com.cmpe202.g62.model.Request;
import com.cmpe202.g62.model.Vehicle;
import com.cmpe202.g62.notification.MemberNotifier;
import com.cmpe202.g62.notification.MemberObserver;
import com.cmpe202.g62.payment.PaymentController;
import com.cmpe202.g62.reports.ReportSetup;
import com.cmpe202.g62.ride.RideController;

/**
 * This class is Facade of facade pattern
 * All the member requests goes through this class
 *
 */
public class RequestController{

	private RideController rideController;
	private PaymentController paymentController;
	private MemberController memberController;
	private ReportSetup reportGenerator;

	public RequestController(){
		rideController = new RideController();
		paymentController = new PaymentController();
		memberController = new MemberController();
		reportGenerator = new ReportSetup();
	}

	/**
	 * This method registers member
	 * @param member
	 * @return Member
	 */
	public Member registerMember(Member member){
		member = memberController.registerMember(member);
		return member;
	}
	
	/**
	 * This method registers vehicle
	 * @param vehicle
	 * @return Vehicle
	 */
	public Vehicle registerVehicle(Vehicle vehicle){
		VehicleDAO vehicleDAO = new VehicleDAO();
		vehicle = vehicleDAO.registerVehicle(vehicle);
		return vehicle;
	}
	
	/**
	 * This method add payment details
	 * @param payment
	 * @return Payment
	 */
	public Payment addPaymentDetails(Payment payment){
		payment = paymentController.addPaymentDetails(payment);
		return payment;
	}
	
	/**
	 * This method updates membership
	 * @param member
	 * @param premiumType
	 * @return Member
	 */
	public Member updateMembership(Member member, String premiumType){
		member = memberController.updateMembership(member, premiumType);
		return member;
	}

	/**
	 * This method updates member details
	 * @param member
	 * @return Member
	 */
	public Member updateMember(Member member){
		member = memberController.updateMember(member);
		return member;
	}

	/**
	 * THis method deletes member
	 * @param member
	 */
	public void deleteMember(Member member){
		memberController.deleteMember(member);
	}

	/**
	 * This method signs in member
	 * @param username
	 * @param password
	 * @return Member
	 */
	public Member signInMember(String username, String password){
		Member member = memberController.getMember(username, password);
		return member;
	}

	/**
	 * This method schedules ride
	 * @param request
	 * @return Request
	 */
	public Request scheduleRide(Request r){
		r = rideController.receiveRideRequest(r);
		return r;
	}

	/**
	 * THis method processes the ride
	 * @param request
	 * @return Request
	 */
	public Request processRide(Request r){
		r = rideController.processRideRequest(r);
		return r;
	}

	/**
	 * This method notifies reported issue
	 * @param member
	 * @param message
	 */
	public void reportIssue(Member member, String message){
		MemberObserver memberObserver = new MemberObserver(member);
		MemberNotifier notifier = new MemberNotifier(member);
		notifier.addObserver(memberObserver);
		notifier.notifyObservers(null, message);
	}

	/**
	 * This method completes the ride
	 * @param r
	 * @return Request
	 */
	public Request completeRide(Request r){
		r = rideController.completeRideRequest(r);
		return r;
	}
	
	/**
	 * This method generates passenger ride report
	 */
	public void passengerRideReport() {
		reportGenerator.generatePassengerRideReport();
	}

	/**
	 * THis method generates member report
	 */
	public void memberReport() {
		reportGenerator.generateMemberReport();	
	}

}
