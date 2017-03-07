package com.cmpe202.g62.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cmpe202.g62.model.CardDetails;
import com.cmpe202.g62.model.PayPal;
import com.cmpe202.g62.model.Payment;
import com.cmpe202.g62.model.RideDetails;
import com.cmpe202.g62.util.DBConnection;

/**
 * This class handles payment operations with database
 *
 */
public class PaymentDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/**
	 * This method adds payment details
	 * @param payment
	 * @return
	 */
	public Payment addPaymentDetails(Payment payment){
		if(payment.getPaymentMode().equals("creditCard"))
			payment = addCardDetails(payment);
		else
			payment = addPaypalDetails(payment);
		return payment;
	}

	/**
	 * THis method adds paypal details
	 * @param payment
	 * @return
	 */
	private Payment addPaypalDetails(Payment payment){
		try {
			conn = DBConnection.getConnection();

			pstmt = conn.prepareStatement("INSERT INTO paypal(member_id, email, password, card_valid_date) VALUES (?, ?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1,payment.getMember().getMemberId());
			pstmt.setString(2, ((PayPal) payment).getEmail());
			pstmt.setString(3, ((PayPal) payment).getPassword());
			pstmt.setDate(4, (java.sql.Date) payment.getValidDate());

			int result = pstmt.executeUpdate();
			if(result > 0){
				rs = pstmt.getGeneratedKeys();
				while(rs.next()){
					payment.setPaymentId(rs.getInt(1));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Payment not successful!");
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, null);
		}
		return payment;
	}

	/**
	 * This method adds credit card details
	 * @param payment
	 * @return
	 */
	private Payment addCardDetails(Payment payment){
		try {
			conn = DBConnection.getConnection();

			pstmt = conn.prepareStatement("INSERT INTO credit_card(member_id, card_number, card_valid_date) VALUES (?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1,payment.getMember().getMemberId());
			pstmt.setLong(2, ((CardDetails) payment).getCardNumber());
			pstmt.setDate(3, (java.sql.Date) payment.getValidDate());

			int result = pstmt.executeUpdate();
			if(result > 0){
				rs = pstmt.getGeneratedKeys();
				while(rs.next()){
					payment.setPaymentId(rs.getInt(1));
				}
			}
		} catch (Exception e) {
			System.out.println("Payment not successful!");
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, null);
		}
		return payment;
	}

	/**
	 * This method validates credit card details
	 * @param cardDetails
	 * @return
	 */
	public boolean validateCardDetails(CardDetails cardDetails){
		try{
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("SELECT credit_card_id, member_id, card_number, card_valid_date FROM credit_card where card_number = ? and card_valid_date = ?");
			pstmt.setLong(1, cardDetails.getCardNumber());
			pstmt.setDate(2, (java.sql.Date)cardDetails.getValidDate());
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getInt(2) == cardDetails.getMember().getMemberId()){
					return true;
				}else{
					return false;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, null);
		}
		return false;
	}

	/**
	 * This method validates paypal details
	 * @param paypal
	 * @return
	 */
	public boolean validatePaypalDetails(PayPal paypal){
		try{
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("SELECT paypal_id, member_id, email, password, card_valid_date FROM paypal where email = ? and password = ? and card_valid_date = ?");
			pstmt.setString(1, paypal.getEmail());
			pstmt.setString(2, paypal.getPassword());
			pstmt.setDate(3, (java.sql.Date)paypal.getValidDate());
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getInt(2) == paypal.getMember().getMemberId()){
					return true;
				}else{
					return false;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, null);
		}
		return false;
	}

	/**
	 * This method makes payment
	 * @param rideDetails
	 * @return
	 */
	public int makePayment(RideDetails rideDetails){
		int result = 0;
		try{
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("UPDATE ride_details SET paid=true WHERE ride_details_id=?");
			pstmt.setInt(1, rideDetails.getRideDetailsId());
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("Payment not successful!");
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, pstmt, null);
		}
		return result;
	}

}
