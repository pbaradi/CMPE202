package com.cmpe202.g62.member;

import com.cmpe202.g62.model.Member;

/**
 * This class is the component class of decorator pattern used for premium membership
 *
 */
public abstract class Membership {
	
	/**
	 * This method gets member details
	 * @param username
	 * @param password
	 * @return Member
	 */
	public abstract Member getMemberDetails(String username, String password);
	
	/**
	 * This method registers member
	 * @param member
	 * @return
	 */
	public abstract Member registerMember(Member member);
	
	/**
	 * THis method updates member
	 * @param member
	 * @return member
	 */
	public abstract Member updateMember(Member member);
	
	/**
	 * THis method deletes member
	 * @param member
	 */
	public abstract void deleteMember(Member member);
	
	
}
