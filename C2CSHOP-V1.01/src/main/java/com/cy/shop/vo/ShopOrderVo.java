package com.cy.shop.vo;

import java.io.Serializable;

import lombok.Data;
@Data
public class ShopOrderVo implements Serializable{
	private static final long serialVersionUID = 3953506129568155914L;
	private Integer orderId;
	private Integer prodId;
	private String prodName;
	private Double prodPrice;
	private Integer payAmount;
	private String photo;
	private String prodUnit;
}
