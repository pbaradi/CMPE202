package com.cmpe202.g62.member;

import com.cmpe202.g62.member.impl.GoldMembership;
import com.cmpe202.g62.member.impl.RegularMembership;
import com.cmpe202.g62.member.impl.SilverMembership;
import com.cmpe202.g62.model.Member;
import com.cmpe202.g62.model.PremiumMember;
import com.cmpe202.g62.notification.MemberNotifier;
import com.cmpe202.g62.notification.MemberObserver;
import com.cmpe202.g62.notification.Observer;

/**
 * This class handles the member operations
 *
 */
public class MemberController{

	private RegularMembership membership;
	MemberNotifier notifier;

	public MemberController(){
		membership = new RegularMembership();

		notifier = new MemberNotifier();
		Observer observer = new MemberObserver();
		notifier.addObserver(observer);
	}

	/**
	 * THis method fetches member details
	 * @param userName
	 * @param password
	 * @return Member
	 */
	public Member getMember(String userName, String password){
		Member member = membership.getMemberDetails(userName, password);
		if(member!=null)
			notifier.setMember(member, "signedIn");
		else
			notifier.setMember(member, "notSignedIn");
		return member;
	}

	/**
	 * THis method updates member details
	 * @param member
	 * @return Member
	 */
	public Member updateMember(Member member){
		member = membership.updateMember(member);
		return member;
	}

	/**
	 * This method deletes member
	 * @param member
	 */
	public void deleteMember(Member member){
		membership.deleteMember(member);
	}

	/**
	 * This method adds member
	 * @param member
	 * @return Member
	 */
	public Member registerMember(Member member){
		member = membership.registerMember(member);
		notifier.setMember(member, "registeredMember");
		return member;
	}

	/**
	 * This method updates membership details
	 * @param m
	 * @param premiumType
	 * @return Member
	 */
	public Member updateMembership(Member m, String premiumType){
		PremiumMember member = new PremiumMember(m.getMemberId(), m.getUserName(), m.getFirstName(), m.getLastName(), m.getPassword(), m.getEmail(), m.getPhoneNumber(), m.getMemberType(), m.getMessage(), m.getCurrentLocation());
		if(premiumType.equalsIgnoreCase("GOLD")){
			GoldMembership goldMember = new GoldMembership(membership);
			member = goldMember.addGoldMembership(member);
			member = goldMember.updateMembership(member);
		}else if(premiumType.equalsIgnoreCase("SILVER")){
			SilverMembership silverMember = new SilverMembership(membership);
			member = silverMember.addSilverMembership(member);
			member = silverMember.updateMembership(member);
		}else{
			(member).setPremiumCategory("REGULAR");
			member = membership.updateMembership(member);
		}
		notifier.setMember(member, "updatedMembership");
		return member;
	}


}
