package com.cmpe202.g62.reports;

/**
 * This class is the component class of composite pattern and used for reporting
 *
 */
public abstract class ReportComponent {
	
	/**
	 * This method adds report component
	 * @param reportsComponent
	 */
	public void add(ReportComponent reportsComponent){
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This method removes report component
	 * @param reportsComponent
	 */
	public void remove(ReportComponent reportsComponent){
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This method gets a report component
	 * @param index
	 * @return
	 */
	public ReportComponent getComponent(int index){
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This method displays report
	 */
	public void displayReport(){
		throw new UnsupportedOperationException();
	}
}
