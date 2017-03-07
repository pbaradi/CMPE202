package com.cmpe202.g62.member.impl;

import com.cmpe202.g62.dao.MemberDAO;
import com.cmpe202.g62.member.Membership;
import com.cmpe202.g62.model.Member;
import com.cmpe202.g62.model.PremiumMember;

/**
 * This class is concrete decorator of decorator class and handle gold membership
 *
 */
public class GoldMembership extends PremiumMembership {
	
	public GoldMembership(Membership membership) {
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
	 * This method adds gold membership
	 * @param member
	 * @return
	 */
	public PremiumMember addGoldMembership(PremiumMember member){
		member.setPremiumCategory("GOLD");
		return member;
	}
	
	/**
	 * THis method updates gold memebrship
	 * @param member
	 * @return mmeber
	 */
	public PremiumMember updateMembership(PremiumMember member){
		MemberDAO memberDAO = new MemberDAO();
		member = memberDAO.updateMembership(member, "GOLD");
		return member;
	}

}
