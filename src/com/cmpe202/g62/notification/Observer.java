package com.cmpe202.g62.notification;

/**
 * This class is observer of observer pattern
 *
 */
public abstract class Observer {
	
	/**
	 * THis method updates upon subject change
	 * @param notifier
	 * @param object
	 * @param message
	 */
	public abstract void update(Notifier notifier, Object object, String message);

}
