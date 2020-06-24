package com.cy.pj.entity;

public class tb_house_discount {

	//房屋id
	private Integer houseId;
	//连住天数
	private Integer continueDay;
	//连住折扣
	private Integer continue_discount;

	public Integer getContinue_discount() {
		return continue_discount;
	}
	public void setContinue_discount(Integer continue_discount) {
		this.continue_discount = continue_discount;
	}

	public tb_house_discount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public tb_house_discount(Integer houseId, Integer continueDay, Integer continue_discount) {
		super();
		this.houseId = houseId;
		this.continueDay = continueDay;
		this.continue_discount = continue_discount;
	}
	@Override
	public String toString() {
		return "tb_house_discount [houseId=" + houseId + ", continueDay=" + continueDay + ", continue_discount="
				+ continue_discount + "]";
	}

	
}
