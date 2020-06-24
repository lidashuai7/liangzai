package com.cy.shop.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class ShopCartVo implements Serializable{
	private static final long serialVersionUID = 3005822470454244116L;
	private Integer prodId;
	private String prodName;
	private String prodDescribe;
	private Double prodPrice;
	private Integer payAmount;
	private String photo;
	
}
