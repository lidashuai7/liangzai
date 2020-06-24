package com.cy.shop.entity;

import java.io.Serializable;

import lombok.Data;
@Data
public class ShopOrder implements Serializable{
	private static final long serialVersionUID = 5018458503558629863L;
	private Integer orderId;
	private Integer status;
	private Integer payAmount;
	private Integer prodId;
	
}
