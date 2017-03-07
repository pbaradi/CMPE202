package com.cmpe202.g62.reports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cmpe202.g62.model.Location;
import com.cmpe202.g62.util.DBConnection;

/**
 * This class is set up class of composite pattern and set up report data
 *
 */
public class ReportSetup {
		
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/**
	 * This method generates member report
	 */
	public  void generateMemberReport(){
		
		rs = getMemberReport();
		
		ReportComponent driverReport = new ReportGroup("Driver", "Report");
		ReportComponent passengerReport = new ReportGroup("Passenger", "Report");
		ReportComponent silverMemberReport = new ReportGroup("Silver", "Passenger Report");
		ReportComponent goldMemberReport = new ReportGroup("Gold", "Passenger Report");
		ReportComponent memberReport = new ReportGroup("Member", "Report");
		
		memberReport.add(driverReport);
		memberReport.add(passengerReport);
		passengerReport.add(goldMemberReport);
		passengerReport.add(silverMemberReport);
		
		try {
			while(rs.next()){
				if(rs.getString("member_type").equalsIgnoreCase("driver")){
					driverReport.add(new Report(rs.getString("first_name"), rs.getString("last_name")));
				}else if(rs.getString("member_type").equalsIgnoreCase("passenger")){
					if(rs.getString("membership").equalsIgnoreCase("gold")){
						goldMemberReport.add(new Report(rs.getString("first_name"), rs.getString("last_name")));
					}else if(rs.getString("membership").equalsIgnoreCase("silver")){
						silverMemberReport.add(new Report(rs.getString("first_name"), rs.getString("last_name")));
					}else{
						passengerReport.add(new Report(rs.getString("first_name"), rs.getString("last_name")));
					}
				}		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pstmt, rs);
		}
		
		ReportGenerator rc = new ReportGenerator(memberReport);
		rc.generateReport();
		
	}
	
	/**
	 * This method generates passenger ride reports
	 */
	public void generatePassengerRideReport(){
		rs = getPassengerRideDetails();
		ReportComponent goldMemberReport = new ReportGroup("Gold Member", "Report");
		ReportComponent silverMemberReport = new ReportGroup("Silver Member", "Report");
		ReportComponent regularMemberReport = new ReportGroup("Regular Member", "Report");
		ReportComponent passengerMemberReport = new ReportGroup("Passenger Ride", "Report");
		
		passengerMemberReport.add(regularMemberReport);
		passengerMemberReport.add(silverMemberReport);
		passengerMemberReport.add(goldMemberReport);
		
		try {
			while(rs.next()){
				 if(rs.getString("member_type").equalsIgnoreCase("passenger")){
					if(rs.getString("membership").equalsIgnoreCase("gold")){
						goldMemberReport.add(new Report(rs.getString("first_name"), rs.getString("last_name"), new Location(rs.getInt("s_latitude"), rs.getInt("s_longitude")), new Location(rs.getInt("d_latitude"), rs.getInt("d_longitude")),rs.getDouble("ride_amount"), rs.getDouble("parking_amount")));
					}else if(rs.getString("membership").equalsIgnoreCase("silver")){
						silverMemberReport.add(new Report(rs.getString("first_name"), rs.getString("last_name"), new Location(rs.getInt("s_latitude"), rs.getInt("s_longitude")), new Location(rs.getInt("d_latitude"), rs.getInt("d_longitude")),rs.getDouble("ride_amount"), rs.getDouble("parking_amount")));
					}else{
						regularMemberReport.add(new Report(rs.getString("first_name"), rs.getString("last_name"), new Location(rs.getInt("s_latitude"), rs.getInt("s_longitude")), new Location(rs.getInt("d_latitude"), rs.getInt("d_longitude")),rs.getDouble("ride_amount"), rs.getDouble("parking_amount")));
					}
				}		
			}
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(conn, pstmt, rs);
		}
		
		ReportGenerator rc = new ReportGenerator(passengerMemberReport);
		rc.generateReport();
		
		
	}

	/**
	 * This method fetches passenger and his ride details
	 */
	private ResultSet getPassengerRideDetails(){
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("select p.first_name, p.last_name, p.membership, p.member_type, s.latitude as s_latitude,s.longitude as s_longitude, d.latitude as d_latitude,d.longitude as d_longitude, "
					+ "rd.ride_amount,rd.parking_amount from member p, member dr, location s, location d, ride_details rd, ride r "
					+ "where rd.passenger_id = p.member_id and s.location_id = rd.source and d.location_id = rd.destination and p.member_type = ? "
					+ "and r.ride_id = rd.ride_id and dr.member_id = r.driver_id");
			pstmt.setString(1, "Passenger");
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return rs;
	}
	
	/**
	 * This method fetches member details
	 * @return ResultSet
	 */
	private ResultSet getMemberReport(){
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("select m.first_name, m.last_name, m.member_type,m.membership from member m");
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return rs;
	}

}
