package com.cmpe202.g62.reports;

import java.util.ArrayList;

/**
 * This class is the composite class of composite pattern
 * and is used for reporting
 *
 */
public class ReportGroup extends ReportComponent {
	
	private ArrayList<ReportComponent> reportComponents = new ArrayList<ReportComponent>();
	
	private String groupName;
	private String groupDesc;
	
	public ReportGroup(String groupName, String groupDesc) {
		this.groupName = groupName;
		this.groupDesc = groupDesc;
	}

	/**
	 * This method adds a component
	 */
	public void add(ReportComponent reportsComponent) {
		reportComponents.add(reportsComponent);
	}

	/**
	 * This method removes a component
	 */
	public void remove(ReportComponent reportsComponent) {
		reportComponents.remove(reportsComponent);
	}

	/**
	 * This method gets a component
	 */
	public ReportComponent getComponent(int index) {
		return (ReportComponent)reportComponents.get(index);
	}

	/**
	 * This method displays report
	 */
	public void displayReport() {
		System.out.println("\n"+getGroupName()+" "+getGroupDesc());
		System.out.println("-------------------------------------");
		for (ReportComponent reportComponent : reportComponents) {
			reportComponent.displayReport();
		}

	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

}
