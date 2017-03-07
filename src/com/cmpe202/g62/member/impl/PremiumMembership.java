package com.cmpe202.g62.member.impl;

import com.cmpe202.g62.member.Membership;
import com.cmpe202.g62.model.Member;

/**
 * This method acts as decorator class of decorator pattern
 *
 */
public class PremiumMembership extends Membership {
	
	protected Membership membership;
	
	public PremiumMembership(Membership membership) {
		this.membership = membership;
	}
	
	/**
	 * This method gets member details
	 * @param username
	 * @param password
	 * @return Member
	 */
	@Override
	public Member getMemberDetails(String username, String password) {
		return membership.getMemberDetails(username, password);
	}

	/**
	 * This method registers member
	 * @param member
	 * @return
	 */
	@Override
	public Member registerMember(Member member) {
		return membership.registerMember(member);
	}

	/**
	 * THis method updates member
	 * @param member
	 * @return member
	 */
	@Override
	public Member updateMember(Member member) {
		return membership.updateMember(member);
	}

	/**
	 * THis method deletes member
	 * @param member
	 */
	@Override
	public void deleteMember(Member member) {
		membership.deleteMember(member);
	}

}
