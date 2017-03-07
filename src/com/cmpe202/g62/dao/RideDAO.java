package com.cmpe202.g62.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cmpe202.g62.model.Location;
import com.cmpe202.g62.model.Ride;
import com.cmpe202.g62.model.RideDetails;
import com.cmpe202.g62.model.Route;
import com.cmpe202.g62.util.DBConnection;

/**
 * This class handles ride operations with database
 *
 */
public class RideDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/**
	 * THis method creates ride
	 * @param ride
	 * @return
	 */
	public Ride createRide(Ride ride){
		ride = createLocations(ride);
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO ride(driver_id, parking_id, vehicle_id, ride_date, ride_time) VALUES (?, ?, ?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, ride.getDriver().getMemberId());
			if(ride.getParking()!=null)
				pstmt.setInt(2, ride.getParking().getLocationId());
			else
				pstmt.setInt(2, 1);
			pstmt.setInt(3, ride.getVehicle().getVehicleId());
			pstmt.setString(4, ride.getDate());
			pstmt.setString(5, ride.getTime());
			int result = pstmt.executeUpdate();
			if(result > 0){
				rs = pstmt.getGeneratedKeys();
				while(rs.next()){
					ride.setRideId(rs.getInt("ride_id"));
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong. Cannot add ride");
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, null);
		}
		ride = createRideDetails(ride);
		return ride;
	}

	/**
	 * This method creates ride locations
	 * @param ride
	 * @return
	 */
	private Ride createLocations(Ride ride){
		List<RideDetails> rideDetailsList = ride.getRideDetailsList();
		List<RideDetails> newList = new ArrayList<>();
		Location source, destination;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO location(latitude, longitude, parking) VALUES (?, ?, true)");
			for (RideDetails rideDetails : rideDetailsList) {
				source = getLocation(rideDetails.getSource());
				if(source.getLocationId() == 0){

					pstmt.setInt(1, rideDetails.getSource().getLatitude());
					pstmt.setInt(2, rideDetails.getSource().getLongitude());
					pstmt.addBatch();
				}
				destination = getLocation(rideDetails.getDestination());
				if(destination.getLocationId() == 0){
					pstmt.setInt(1, rideDetails.getDestination().getLatitude());
					pstmt.setInt(2, rideDetails.getDestination().getLongitude());
					pstmt.addBatch();
				}
			}
			pstmt.executeBatch();
			
			for (RideDetails rideDetails : rideDetailsList) {
				source = getLocation(rideDetails.getSource());
				rideDetails.setSource(source);
				destination = getLocation(rideDetails.getDestination());
				rideDetails.setDestination(destination);
				newList.add(rideDetails);
			}
			ride.setRideDetailsList(newList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong. Cannot add location");
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, null);
		}
		return ride;
	}

	/**
	 * THis method gets a locatin
	 * @param location
	 * @return
	 */
	public Location getLocation(Location location){
		Connection conne = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		try {
			conne = DBConnection.getConnection();
			stmt = conn.prepareStatement("SELECT location_id FROM location where latitude = ? and longitude = ?");
			stmt.setInt(1, location.getLatitude());
			stmt.setInt(2, location.getLongitude());

			resultSet = stmt.executeQuery();
			if(resultSet.next()){
				location.setLocationId(resultSet.getInt("location_id"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong. Cannot add member");
			e.printStackTrace();
		} finally{
			DBConnection.close(conne, stmt, null);
		}
		return location;
	}

	/**
	 * This method creates ride details
	 * @param ride
	 * @return
	 */
	private Ride createRideDetails(Ride ride){
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO ride_details(passenger_id, source, destination, ride_amount, parking_amount, ride_id) VALUES (?, ?, ?, ?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			List<RideDetails> rideDetailsList = ride.getRideDetailsList();
			List<RideDetails> newList;
			for (RideDetails rideDetails : rideDetailsList) {
				pstmt.setInt(1, rideDetails.getPassenger().getMemberId());
				pstmt.setInt(2, rideDetails.getSource().getLocationId());
				pstmt.setInt(3, rideDetails.getDestination().getLocationId());
				pstmt.setDouble(4, rideDetails.getRideAmount());
				pstmt.setDouble(5, rideDetails.getParkingAmount());
				pstmt.setInt(6, ride.getRideId());
				pstmt.addBatch();
			}

			int result[] = pstmt.executeBatch();
			if(result.length > 0){
				newList = new ArrayList<>();
				rs = pstmt.getGeneratedKeys();
				for (RideDetails rideDetails : rideDetailsList) {
					if(rs.next()){
						rideDetails.setRideDetailsId(rs.getInt("ride_details_id"));
						newList.add(rideDetails);
					}
				}
				ride.setRideDetailsList(newList);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong. Cannot add member");
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, null);
		}
		return ride;
	}

	/**
	 * This method updates ride details
	 * @param ride
	 * @return
	 */
	public Ride updateRideDetails(Ride ride){
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("UPDATE ride_details SET ride_amount=?, parking_amount=? WHERE ride_details_id=?");
			List<RideDetails> rideDetailsList = ride.getRideDetailsList();
			for (RideDetails rideDetails : rideDetailsList) {
				pstmt.setDouble(1, rideDetails.getRideAmount());
				pstmt.setDouble(2, rideDetails.getParkingAmount());
				pstmt.setInt(3, rideDetails.getRideDetailsId());
				pstmt.addBatch();
			}

			int result[] = pstmt.executeBatch();
			if(result.length>0){
				System.out.println();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong. Cannot add member");
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, null);
		}
		return ride;
	}

	/**
	 * This method creates route
	 * @param ride
	 * @return
	 */
	public Ride createRoute(Ride ride){
		List<Route> routeList = ride.getRoute();
		List<Route> newList;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO route(ride_id, location_id, route_seq) VALUES (?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, ride.getRideId());

			for (Route route : routeList) {
				pstmt.setInt(2, route.getLocation().getLocationId());
				pstmt.setInt(3, route.getSeq());
				pstmt.addBatch();
			}
			int result[] = pstmt.executeBatch();
			if(result.length > 0){
				newList = new ArrayList<>();
				rs = pstmt.getGeneratedKeys();
				for (Route route : routeList) {
					if(rs.next()){
						route.setRoute_id(rs.getInt("route_id"));
						newList.add(route);
					}
				}
				ride.setRoute(newList);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong. Cannot add member");
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, null);
		}
		return ride;
	}

}
