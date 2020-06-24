package com.cy.pj.entity;

import java.util.Date;

public class tb_order_item {

	
	//订单详情id
	private Integer itemId;
	
	//订单表id
	private Integer orderId;
	
	//唯一订单id
	private String orderNumber;
	
	//预定人id
	private Integer bespeakId;
	
	//入住人id
	private Integer lodgerId;
	
	//入住人数
	private Integer lodgerNumber;
	
	//房屋id
	private Integer houseId;
	
	//预定房屋数量
	private Integer houseNumber;
	
	//预定开始时间
	private Date reservationStart;
	
	//预定结束时间
	private Date reservationEnd;
	
	//预定天数
	private Integer reservationDay;
	
	//基础价
	private Double basePrice;
	
	//总金额
	private Double grossPrice;
	
	//交易状态0未缴费，1已交押金，2已付清
	private Integer tradeStatus;
	
	//评分
	private Integer score;
	
	//评论
	private String comment;

	public tb_order_item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getBespeakId() {
		return bespeakId;
	}

	public void setBespeakId(Integer bespeakId) {
		this.bespeakId = bespeakId;
	}

	public Integer getLodgerId() {
		return lodgerId;
	}

	public void setLodgerId(Integer lodgerId) {
		this.lodgerId = lodgerId;
	}

	public Integer getLodgerNumber() {
		return lodgerNumber;
	}

	public void setLodgerNumber(Integer lodgerNumber) {
		this.lodgerNumber = lodgerNumber;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public Integer getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}

	public Date getReservationStart() {
		return reservationStart;
	}

	public void setReservationStart(Date reservationStart) {
		this.reservationStart = reservationStart;
	}

	public Date getReservationEnd() {
		return reservationEnd;
	}

	public void setReservationEnd(Date reservationEnd) {
		this.reservationEnd = reservationEnd;
	}

	public Integer getReservationDay() {
		return reservationDay;
	}

	public void setReservationDay(Integer reservationDay) {
		this.reservationDay = reservationDay;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public Double getGrossPrice() {
		return grossPrice;
	}

	public void setGrossPrice(Double grossPrice) {
		this.grossPrice = grossPrice;
	}

	public Integer getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public tb_order_item(Integer itemId, Integer orderId, String orderNumber, Integer bespeakId, Integer lodgerId,
			Integer lodgerNumber, Integer houseId, Integer houseNumber, Date reservationStart, Date reservationEnd,
			Integer reservationDay, Double basePrice, Double grossPrice, Integer tradeStatus, Integer score,
			String comment) {
		super();
		this.itemId = itemId;
		this.orderId = orderId;
		this.orderNumber = orderNumber;
		this.bespeakId = bespeakId;
		this.lodgerId = lodgerId;
		this.lodgerNumber = lodgerNumber;
		this.houseId = houseId;
		this.houseNumber = houseNumber;
		this.reservationStart = reservationStart;
		this.reservationEnd = reservationEnd;
		this.reservationDay = reservationDay;
		this.basePrice = basePrice;
		this.grossPrice = grossPrice;
		this.tradeStatus = tradeStatus;
		this.score = score;
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "tb_order_item [itemId=" + itemId + ", orderId=" + orderId + ", orderNumber=" + orderNumber
				+ ", bespeakId=" + bespeakId + ", lodgerId=" + lodgerId + ", lodgerNumber=" + lodgerNumber
				+ ", houseId=" + houseId + ", houseNumber=" + houseNumber + ", reservationStart=" + reservationStart
				+ ", reservationEnd=" + reservationEnd + ", reservationDay=" + reservationDay + ", basePrice="
				+ basePrice + ", grossPrice=" + grossPrice + ", tradeStatus=" + tradeStatus + ", score=" + score
				+ ", comment=" + comment + "]";
	}
	
	
}
