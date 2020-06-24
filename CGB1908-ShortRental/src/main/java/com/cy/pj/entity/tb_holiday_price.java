package com.cy.pj.entity;

import java.util.Date;

public class tb_holiday_price {

	//房屋id
	private Integer houseId;
	//节日日期
	private Date holiday;
	//价格
	private Double price;

	public Date getHoliday() {
		return holiday;
	}
	public void setHoliday(Date holiday) {
		this.holiday = holiday;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public tb_holiday_price() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getHouseId() {
		return houseId;
	}
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	public tb_holiday_price(Integer houseId, Date holiday, Double price) {
		super();
		this.houseId = houseId;
		this.holiday = holiday;
		this.price = price;
	}
	@Override
	public String toString() {
		return "tb_holiday_price [houseId=" + houseId + ", holiday=" + holiday + ", price=" + price + "]";
	}

	
}
