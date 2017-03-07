package com.cmpe202.g62.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cmpe202.g62.model.Location;
import com.cmpe202.g62.model.Member;
import com.cmpe202.g62.model.PremiumMember;
import com.cmpe202.g62.notification.Notifier;
import com.cmpe202.g62.util.DBConnection;

/**
 * This class handles member operations with database
 *
 */
public class MemberDAO extends Notifier{

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/**
	 * THis method creates member
	 * @param m
	 * @return
	 */
	public Member createMember(Member m){
		try {
			conn = DBConnection.getConnection();
			if(!checkMemberExists(m.getUserName(), m.getPassword())){
				pstmt = conn.prepareStatement("INSERT INTO Member(username, first_name, last_name, password, member_type, email_id, phone_number, location_id, membership,active) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, m.getUserName());
				pstmt.setString(2, m.getFirstName());
				pstmt.setString(3, m.getLastName());
				pstmt.setString(4, m.getPassword());
				pstmt.setString(5, m.getMemberType());
				pstmt.setString(6, m.getEmail());
				pstmt.setInt(7, m.getPhoneNumber());
				pstmt.setInt(8, 1);
				pstmt.setString(9, "REGULAR");
				pstmt.setString(10, "ACTIVE");
				int result = pstmt.executeUpdate();
				if(result > 0){
					rs = pstmt.getGeneratedKeys();
					while(rs.next()){
						m.setMemberId(rs.getInt(1));
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong. Cannot add member");
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, null);
		}
		return m;
	}

	/**
	 * THis method updates member
	 * @param m
	 * @return
	 */
	public Member updateMember(Member m){
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("UPDATE member SET first_name=?, last_name=?, password=?, member_type=?, email_id =?, phone_number =? WHERE username=?");
			pstmt.setString(1, m.getFirstName());
			pstmt.setString(2, m.getLastName());
			pstmt.setString(3, m.getPassword());
			pstmt.setString(4, m.getMemberType());
			pstmt.setString(5, m.getEmail());
			pstmt.setInt(6, m.getPhoneNumber());
			pstmt.setString(7, m.getUserName());
			int result = pstmt.executeUpdate();
			if(result>0)
				System.out.println("Updated member successfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong. Cannot update member");
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, null);
		}
		return m;
	}


	/**
	 * THis method deletes member
	 * @param member
	 */
	public void deleteMember(Member member){
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("UPDATE member set active=? WHERE username=?");
			pstmt.setString(1, "INACTIVE");
			pstmt.setString(2, member.getUserName());
			int result = pstmt.executeUpdate();
			if(result>0){
				System.out.println("Member with user name "+member.getUserName()+" deleted");

			}else{
				System.out.println("No member with user name "+member.getUserName()+" exists.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong. Cannot delete member");
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, null);
		}
	}


	/**
	 * This method get a member
	 * @param username
	 * @param password
	 * @return
	 */
	public Member getMember(String username, String password){
		Member m=null;
		Location l;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("SELECT m.member_id, m.username, m.first_name, m.last_name, m.password, m.member_type, m.email_id, m.phone_number, l.location_id, l.latitude, l.longitude, l.parking FROM member m, location l where username=? and password=? and m.location_id = l.location_id and active =?");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, "ACTIVE");
			rs = pstmt.executeQuery();
			while(rs.next()){
				l = new Location(rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getBoolean(12));
				m = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7), rs.getInt(8), rs.getString(6), null, l);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong. Cannot fetch member");
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, rs);
		}
		return m;
	}

	/**
	 * This method checks if member exists
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean checkMemberExists(String username, String password){
		Connection conne = null;
		PreparedStatement stmt = null;
		ResultSet rSet = null;
		try {
			conne = DBConnection.getConnection();
			stmt = conne.prepareStatement("SELECT m.member_id, m.username, m.first_name, m.last_name, m.password, m.member_type, m.email_id, m.phone_number, l.location_id, l.latitude, l.longitude, l.parking FROM member m, location l where username=? and password=? and m.location_id = l.location_id and active =?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, "ACTIVE");
			rSet = stmt.executeQuery();
			if(rSet.next()){
				return true;
			}
		} catch (Exception e) {
			System.out.println("Something went wrong. Cannot fetch member");
			e.printStackTrace();
		} finally{
			DBConnection.close(conne, stmt, rSet);
		}
		return false;
	}


	/**
	 * THis method updates membership
	 * @param member
	 * @param premiumValue
	 * @return
	 */
	public PremiumMember updateMembership(PremiumMember member, String premiumValue){
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("UPDATE member SET membership=? WHERE member_id=?");
			pstmt.setString(1,premiumValue);
			pstmt.setInt(2,member.getMemberId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong!!");
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, null);
		}
		return member;
	}


}
