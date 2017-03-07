package com.cmpe202.g62.notification;

import com.cmpe202.g62.model.Member;


/**
 * This class is concrete implementor of subject of observer pattern and used for member notifications
 *
 */
public class MemberNotifier extends Notifier {
	
	private Member member;
	
	public MemberNotifier() {
		super();
	}
	
	public MemberNotifier(Member member) {
		this.member = member;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member, String message) {
		this.member = member;
		notifyObservers(member, message);
	}
	
	

}
