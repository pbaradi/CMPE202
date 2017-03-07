package com.cmpe202.g62.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cmpe202.g62.model.Location;
import com.cmpe202.g62.util.DBConnection;

/**
 * This class handles parking operations with database
 *
 */
public class ParkingDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/**
	 * This method registers location
	 * @param location
	 * @return
	 */
	public Location registerLocation(Location location){
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO location(latitude, longitude, parking) VALUES (?, ?, ?);",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, location.getLatitude());
			pstmt.setInt(2, location.getLongitude());
			pstmt.setBoolean(3, location.isParking());
			int result = pstmt.executeUpdate();
			if(result > 0){
				rs = pstmt.getGeneratedKeys();
				while(rs.next()){
					location.setLocationId(rs.getInt("location_id"));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong. Cannot register Vehicle");
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, null);
		}
		return location;
	}

	/**
	 * THis method fetches available parking
	 * @return
	 */
	public List<Location> getAvailableParking(){
		List<Location> locationList = new ArrayList<Location>();
		Location location;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("select * from location where parking = true");
			rs = pstmt.executeQuery();
			while(rs.next()){
				location = new Location(rs.getInt("location_id"), rs.getInt("latitude"), rs.getInt("longitude"), rs.getBoolean("parking"));
				locationList.add(location);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong. Cannot register Vehicle");
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, null);
		}
		return locationList;
	}

}
