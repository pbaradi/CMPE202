package com.cmpe202.g62.notification;

import java.util.ArrayList;

/**
 * This class is subject of observer pattern
 *
 */
public class Notifier {
	
	private ArrayList<Observer> notifications = new ArrayList<Observer>();

	/**
	 * This method adds observer
	 * @param n
	 */
	public void addObserver(Observer n){
		notifications.add(n);
	}
	
	/**
	 * This method removes observer
	 * @param n
	 */
	public void removeObserver(Observer n){
		notifications.remove(n);
	}
	
	/**
	 * This method notifies observer
	 * @param n
	 */
	public void notifyObservers(Object object, String message){
		for (Observer notification : notifications) {
			notification.update(this, object, message);
		}
	}

}
