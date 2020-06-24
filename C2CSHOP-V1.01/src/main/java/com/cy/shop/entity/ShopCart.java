package com.cy.shop.entity;

import java.io.Serializable;

import lombok.Data;
@Data
public class ShopCart implements Serializable {

	private static final long serialVersionUID = 4233499509208658129L;

	private Integer id;//这条购物车记录的id
	private Integer userId;//用户id
	private Integer prodId;//商品id
	private Integer payAmount;//购买数量
	
	
	
	
	

}
