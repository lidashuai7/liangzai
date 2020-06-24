package com.cy.pj.entity;

import java.util.Date;

public class tb_order_master {

	//订单id
	private Integer orderId;
	//订单号
	private String orderNumber;
	//交易状态1成功2失败
	private Integer tradeStatus;
	//订单状态0取消，1确定，2离店
	private Integer orderStatus;
	//订单金额
	private Double orderPrice;
	//付款金额
	private Double payPrice;
	//订单创建时间
	private Date createTime;
	//订单支付时间
	private Date payTime;


	public tb_order_master() {
		super();
		// TODO Auto-generated constructor stub
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


	public Integer getTradeStatus() {
		return tradeStatus;
	}


	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}


	public Integer getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}


	public Double getOrderPrice() {
		return orderPrice;
	}


	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}


	public Double getPayPrice() {
		return payPrice;
	}


	public void setPayPrice(Double payPrice) {
		this.payPrice = payPrice;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Date getPayTime() {
		return payTime;
	}


	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}


	public tb_order_master(Integer orderId, String orderNumber, Integer tradeStatus, Integer orderStatus,
			Double orderPrice, Double payPrice, Date createTime, Date payTime) {
		super();
		this.orderId = orderId;
		this.orderNumber = orderNumber;
		this.tradeStatus = tradeStatus;
		this.orderStatus = orderStatus;
		this.orderPrice = orderPrice;
		this.payPrice = payPrice;
		this.createTime = createTime;
		this.payTime = payTime;
	}


	@Override
	public String toString() {
		return "tb_order_master [orderId=" + orderId + ", orderNumber=" + orderNumber + ", tradeStatus=" + tradeStatus
				+ ", orderStatus=" + orderStatus + ", orderPrice=" + orderPrice + ", payPrice=" + payPrice
				+ ", createTime=" + createTime + ", payTime=" + payTime + "]";
	}


	
}
