package com.cy.shop.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ShopProd implements Serializable{
	private static final long serialVersionUID = -7118327678114058965L;
	private Integer id;
	private String name;
	private String prodDescribe;
	private String note;
	private String placeOfOrigin;
	private String mainPhoto;
	private String smallPhoto;
	private String bigPhoto;
	private Double price;
	private String kind;
	private Date timeToMarket;
	private String producer;
	private String unit;
	private Integer amount;
	
}
