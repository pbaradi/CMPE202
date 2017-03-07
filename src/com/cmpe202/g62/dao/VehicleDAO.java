package com.cmpe202.g62.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cmpe202.g62.model.Driver;
import com.cmpe202.g62.model.Location;
import com.cmpe202.g62.model.Member;
import com.cmpe202.g62.model.Vehicle;
import com.cmpe202.g62.util.DBConnection;

/**
 * This class handles vehicle operations with database
 *
 */
public class VehicleDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/**
	 * This register vehicle
	 * @param vehicle
	 * @return
	 */
	public Vehicle registerVehicle(Vehicle vehicle){
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO vehicle(vehicle_type, vehicle_name, available, member_id) VALUES (?, ?, ?, ?);",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, vehicle.getVehicleType());
			pstmt.setString(2, vehicle.getVehicleName());
			pstmt.setBoolean(3, true);
			pstmt.setInt(4, vehicle.getOwner().getMemberId());
			int result = pstmt.executeUpdate();
			if(result > 0){
				rs = pstmt.getGeneratedKeys();
				while(rs.next()){
					vehicle.setVehicleId(rs.getInt("vehicle_id"));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong. Cannot register Vehicle");
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, null);
		}
		return vehicle;
	}

	/**
	 * THis method gets availble vehicles
	 * @return
	 */
	public List<Vehicle> getAvailableVehicles(){
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		Vehicle vehicle;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("select v.vehicle_id, v.vehicle_type, v.vehicle_name, m.member_id, m.username, m.first_name, m.last_name, m.password, m.email_id, m.phone_number, l.location_id, l.latitude, l.longitude from vehicle v, member m, location l where v.available =true and m.member_id = v.member_id and m.member_type = ? and l.location_id = m.location_id");
			pstmt.setString(1, "Driver");
			rs = pstmt.executeQuery();
			while(rs.next()){
				Location location = new Location(rs.getInt(11), rs.getInt(12), rs.getInt(13));
				Member owner = new Driver(rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), "Driver", null, location, null);
				vehicle = new Vehicle(rs.getInt(1), rs.getString(2), rs.getString(3), owner, true);
				((Driver)owner).setVehicle(vehicle);
				vehicleList.add(vehicle);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong. Cannot register Vehicle");
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, null);
		}
		return vehicleList;
	}
}
