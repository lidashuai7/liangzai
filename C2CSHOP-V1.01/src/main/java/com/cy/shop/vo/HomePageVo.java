package com.cy.shop.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class HomePageVo implements Serializable {
	
	private static final long serialVersionUID = 9045375836743030111L;
	private Integer id;
	private String name;
	private String mainPhoto;
	private Double price;
}
