package com.cmpe202.g62.ride;

import com.cmpe202.g62.model.Request;

/**
 * This interface is the state interface of state pattern and is used for managing ride
 * @author pavanibaradi
 *
 */
public interface State {
	
	/**
	 * This method manages ride request
	 * @param request
	 * @return Request
	 */
	public Request receiveRequest(Request request);
	
	/**
	 * This method manages ride processing
	 * @param request
	 * @return Request
	 */
	public Request processRequest(Request request);
	
	/**
	 * This method manages ride completion
	 * @param request
	 * @return Request
	 */
	public Request completeRequest(Request request);

}
