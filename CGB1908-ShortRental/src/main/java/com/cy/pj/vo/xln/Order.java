package com.cy.pj.vo.xln;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

public class Order implements Serializable {
	private static final long serialVersionUID = -1580144261003133937L;
	private String orderNum;
	private String houseId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	private Integer affirmCancel;
	private String picturePath;
	private String houseName;
	private String houseAddress;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date checkInTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date checkOutTime;
	private Double grossPrice;
	private Integer tradeStatus;
	private Integer status;
	private String username;

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAffirmCancel() {
		if(affirmCancel==1) {
			return "房东已确认";
		}else if(affirmCancel==2) {
			return "房东待确认";
		}else {
			return "房东已取消";
		}
	}

	public void setAffirmCancel(Integer affirmCancel) {
		this.affirmCancel = affirmCancel;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public Date getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}

	public Date getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public Double getGrossPrice() {
		return grossPrice;
	}

	public void setGrossPrice(Double grossPrice) {
		this.grossPrice = grossPrice;
	}

	public String getTradeStatus() {
		if(tradeStatus==1) {
			return "已支付";
		}else if(tradeStatus==3){
			return "待支付";
		}else {
			return "已取消";
		}
	}
	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getStatus() {
		if(status==1) {
			return "待入住";
		}else if(status==2) {
			return "已入住";
		}else {
			return "已离店";
		}
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Order [orderNum=" + orderNum + ", houseId=" + houseId + ", createTime=" + createTime + ", affirmCancel="
				+ affirmCancel + ", picturePath=" + picturePath + ", houseName=" + houseName + ", houseAddress="
				+ houseAddress + ", checkInTime=" + checkInTime + ", checkOutTime=" + checkOutTime + ", grossPrice="
				+ grossPrice + ", tradeStatus=" + tradeStatus + ", status=" + status + ", username=" + username + "]";
	}
	

}
