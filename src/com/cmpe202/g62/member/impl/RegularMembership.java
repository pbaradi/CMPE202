package com.cmpe202.g62.member.impl;

import com.cmpe202.g62.dao.MemberDAO;
import com.cmpe202.g62.member.Membership;
import com.cmpe202.g62.model.Member;
import com.cmpe202.g62.model.PremiumMember;

/**
 * This class is concrete component of decorator class and handle regular membership
 *
 */
public class RegularMembership extends Membership {
	
	private MemberDAO memberDAO;
	
	public RegularMembership() {
		memberDAO = new MemberDAO();
	}

	/**
	 * This method gets member details
	 * @param username
	 * @param password
	 * @return Member
	 */
	@Override
	public Member getMemberDetails(String username, String password) {
		Member member = memberDAO.getMember(username, password);
		return member;
	}

	/**
	 * This method registers member
	 * @param member
	 * @return
	 */
	@Override
	public Member registerMember(Member member) {
		member = memberDAO.createMember(member);
		return member;
	}

	/**
	 * THis method updates member
	 * @param member
	 * @return member
	 */
	@Override
	public Member updateMember(Member member) {
		member = memberDAO.updateMember(member);
		return member;
	}

	/**
	 * THis method deletes member
	 * @param member
	 */
	@Override
	public void deleteMember(Member member) {
		memberDAO.deleteMember(member);
	}
	
	/**
	 * THis method updates regular memebrship
	 * @param member
	 * @return mmeber
	 */
	public PremiumMember updateMembership(PremiumMember member){
		MemberDAO memberDAO = new MemberDAO();
		member = memberDAO.updateMembership(member, "REGULAR");
		return member;
	}

}
