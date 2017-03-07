package com.cmpe202.g62.notification;

import com.cmpe202.g62.model.Member;
import com.cmpe202.g62.model.PremiumMember;


/**
 * This class is concrete observer of observer pattern and used to observe member
 *
 */
public class MemberObserver extends Observer {

	private Member member;

	public MemberObserver() {
		super();
	}

	public MemberObserver(Member member){
		this.member = member;
	}

	/**
	 * This method updates member of any member changes
	 */
	@Override
	public void update(Notifier subject, Object object, String message) {
		System.out.println("=================================================");
		if(object == null){
			if(message.equalsIgnoreCase("notSignedIn"))
				System.out.println("Please enter valid username and password to sign in");
			else
				System.out.println("Dear "+ member.getFirstName() + " "+ member.getLastName() +" "+message);
		}else if(object!=null && object instanceof Member){
			Member m = (Member)object;
			if(message.equalsIgnoreCase("registeredMember")){
				System.out.println("Dear "+ m.getFirstName() + " "+ m.getLastName() +", your member ship is confirmed as "+ m.getMemberType());
			}else if(message.equalsIgnoreCase("signedIn")){
				System.out.println("Dear member, you have signed in as "+ m.getFirstName() + " "+ m.getLastName());
			}else if(object!=null && object instanceof PremiumMember){
				PremiumMember member = (PremiumMember)object;
				if(message.equalsIgnoreCase("updatedMembership") && member.getMemberType().equalsIgnoreCase("passenger")){
					System.out.println("Dear "+ m.getFirstName() + " "+ member.getLastName()+", congratulations! Your are a "+member.getPremiumCategory()+" member");
				}
			}
		}
		System.out.println("=================================================");
	}

}
