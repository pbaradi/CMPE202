package com.cmpe202.g62.notification;

import com.cmpe202.g62.model.Ride;
import com.cmpe202.g62.model.RideDetails;
import com.cmpe202.g62.model.Route;

/**
 * This class is concrete observer of observer pattern and used to observe member
 *
 */
public class RideObserver extends Observer {

	public RideObserver(){
		super();
	}

	/**
	 * This method updates member of ride notifications
	 */
	@Override
	public void update(Notifier subject, Object object, String message) {	
		if(object instanceof Ride){
			System.out.println("=================================================");
			Ride ride = (Ride)object;
			if(message.equalsIgnoreCase("RequestingRide")){
				for (RideDetails rideDetails : ride.getRideDetailsList()) {
					System.out.println("Dear "+rideDetails.getPassenger().getFirstName() +" "+ rideDetails.getPassenger().getLastName()+", your request to ride from "+rideDetails.getSource().toString() +" to "+rideDetails.getDestination().toString()+" is confirmed.");

				}
			}else if(message.equalsIgnoreCase("RequestRide")){
				for (RideDetails rideDetails : ride.getRideDetailsList()) {
					System.out.println("Dear "+rideDetails.getPassenger().getFirstName() +" "+ rideDetails.getPassenger().getLastName()+", please request ride.");

				}
			}else if(message.equalsIgnoreCase("RequestedRide")){
				for (RideDetails rideDetails : ride.getRideDetailsList()) {
					System.out.println("Dear "+rideDetails.getPassenger().getFirstName() +" "+ rideDetails.getPassenger().getLastName()+", ride already requested.");

				}
			}else if(message.equalsIgnoreCase("scheduleRide")){
				for (RideDetails rideDetails : ride.getRideDetailsList()) {
					System.out.println("Dear "+rideDetails.getPassenger().getFirstName() +" "+ rideDetails.getPassenger().getLastName()+", your ride from "+rideDetails.getSource().toString() +" to "+rideDetails.getDestination().toString()+" is scheduled.");
					System.out.println("Assigned Vehicle is "+ride.getVehicle().toString());
					System.out.println("Assigned Driver is "+ride.getDriver().toString());
					if(ride.getParking()!=null){
						System.out.println("Assigned Parking is "+ride.getParking().toString());
					}
				}
			}else if(message.equalsIgnoreCase("ProcessingRide")){
				for (RideDetails rideDetails : ride.getRideDetailsList()) {
					System.out.println("Dear "+rideDetails.getPassenger().getFirstName() +" "+ rideDetails.getPassenger().getLastName()+", your trip from "+rideDetails.getSource().toString() +" to "+rideDetails.getDestination().toString()+" is started.");
				}
			}else if(message.equalsIgnoreCase("ProcessRide")){
				System.out.println("Please process the ride request first.");
			}else if(message.equalsIgnoreCase("ProcessedRide")){
				System.out.println("Please the ride request already processed.");
			}else if(message.equalsIgnoreCase("generateRoute")){
				System.out.println("Please take the following route for the ride.");
				for (Route route: ride.getRoute()) {
					System.out.println(route.getLocation().toString());
				}
			}else if(message.equalsIgnoreCase("calculateAmount")){
				for (RideDetails rideDetails : ride.getRideDetailsList()) {
					System.out.println("Dear "+rideDetails.getPassenger().getFirstName() +" "+ rideDetails.getPassenger().getLastName()+",  ride fare is calculated as ");
					System.out.println("Ride amount is "+rideDetails.getRideAmount());
					if(ride.getParking()!=null){
						System.out.println("Parking amount is "+rideDetails.getParkingAmount());
					}
					double total = rideDetails.getRideAmount()+rideDetails.getParkingAmount();
					System.out.println("Total amount is "+ total);

				}
			}else if(message.equalsIgnoreCase("CompleteRide")){
				for (RideDetails rideDetails : ride.getRideDetailsList()) {
					System.out.println("Dear "+rideDetails.getPassenger().getFirstName() +" "+ rideDetails.getPassenger().getLastName()+", your trip from "+rideDetails.getSource().toString() +" to "+rideDetails.getDestination().toString()+" is completed.");
				}
			}else if(message.equalsIgnoreCase("trackRide")){
				System.out.println("Tracking the ride...");
				for (Route route: ride.getRoute()) {
					System.out.println(route.getLocation().toString());
				}
			}
			System.out.println("=================================================");
		}

	}

}
