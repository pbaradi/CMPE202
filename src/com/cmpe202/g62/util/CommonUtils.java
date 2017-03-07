package com.cmpe202.g62.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.cmpe202.g62.model.Location;

/**
 * This method implements reusable methods
 *
 */
public class CommonUtils {

	/**
	 * this method finds the distance between two locations
	 * @param location1
	 * @param location2
	 * @return
	 */
	public static double getDistance(Location location1, Location location2){
		double distance;
		int x1 = location1.getLatitude();
		int y1 = location1.getLongitude();
		int x2 = location2.getLatitude();
		int y2 = location2.getLongitude();
		distance = Math.sqrt(Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2));
		return distance;
	}

	/**
	 * This method compares the objects with other options
	 * @param map
	 * @return
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map ){
		Map<K, V> result = new LinkedHashMap<>();
		Stream<Map.Entry<K, V>> st = map.entrySet().stream();

		st.sorted( Map.Entry.comparingByValue() ).forEachOrdered( e -> result.put(e.getKey(), e.getValue()) );

		return result;
	}

	/**
	 * This method validates date 
	 * @param date
	 * @return boolean
	 */
	public static boolean validateDate(Date date){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Boolean returnValue = false;
		if(date!=null){
			// Todays Date
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			// Card Valid Date if it is later than todays date
			String validityDate = dateFormat.format(date);
			Date cardValidDate = java.sql.Date.valueOf(validityDate);

			if(cardValidDate.compareTo(sqlDate) > 0 || cardValidDate.compareTo(sqlDate) == 0){
				returnValue = true;
			}else {
				returnValue = false;
			}
		}
		return returnValue;
	}

	/**
	 * This method validates email format
	 * @param email
	 * @return boolean
	 * @throws ParseException
	 */
	public static boolean validateEmail(String email) throws ParseException{
		Boolean emailValid = false;
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		if(email!=null){
			java.util.regex.Matcher m = p.matcher(email);
			emailValid = m.matches();
		}
		if(emailValid){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method validates card length
	 * @param card
	 * @return boolean
	 * @throws ParseException
	 */
	public static boolean validateCaardLength(long card) throws ParseException{
		int length = (int) Math.log10(card) + 1;
		if(length == 16){
			return true;
		}else{
			return false;
		}
	}

}
