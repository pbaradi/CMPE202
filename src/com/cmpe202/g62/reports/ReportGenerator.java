package com.cmpe202.g62.reports;

/**
 * This method is client for composite pattern and used for reporting
 *
 */
public class ReportGenerator {
	
	ReportComponent reportList;
	
	public ReportGenerator(ReportComponent reportList) {
		this.reportList = reportList;
	}
	
	/**
	 * This method generates report
	 */
	public void generateReport(){
		reportList.displayReport();
	}	

}
