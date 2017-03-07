package com.cmpe202.g62.member.impl;

import com.cmpe202.g62.dao.MemberDAO;
import com.cmpe202.g62.member.Membership;
import com.cmpe202.g62.model.Member;
import com.cmpe202.g62.model.PremiumMember;

/**
 * This class is concrete decorator of decorator class and handle silver membership
 *
 */
public class SilverMembership extends PremiumMembership {

	public SilverMembership(Membership membership) {
		super(membership);
	}
	
	/**
	 * This method gets gold member details
	 */
	public Member getMemberDetails(String username, String password) {
		Member member = super.getMemberDetails(username, password);
		return member;
	}
	
	/**
	 * This method adds silver membership
	 * @param member
	 * @return
	 */
	public PremiumMember addSilverMembership(PremiumMember member){
		member.setPremiumCategory("SILVER");
		return member;
	}
	
	/**
	 * THis method updates silver memebrship
	 * @param member
	 * @return mmeber
	 */
	public PremiumMember updateMembership(PremiumMember member){
		MemberDAO memberDAO = new MemberDAO();
		member = memberDAO.updateMembership(member, "SILVER");
		return member;
	}

}
