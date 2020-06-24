package com.cy.pj.vo.hlj;


public class Guest {
	
	private Integer guest_id;
	private Integer member_id;//当前登录的e
	private String real_name;
	private String idcard;
	private String tel;
	private String cardtype;
	private String lastname;
	private String firstname;
	
	@Override
	public String toString() {
		return "Guest [guest_id=" + guest_id + ", member_id=" + member_id + ", real_name=" + real_name + ", idcard="
				+ idcard + ", tel=" + tel + ", cardtype=" + cardtype + ", lastname=" + lastname + ", firstname="
				+ firstname + "]";
	}

	public Integer getGuest_id() {
		return guest_id;
	}

	public void setGuest_id(Integer guest_id) {
		this.guest_id = guest_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Guest() {
		super();
	}
	

}
