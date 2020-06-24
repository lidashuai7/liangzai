package com.cy.shop.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class ShopCollector implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1557099561750400325L;
	
	private Integer id;
	private String name;	
	private String mainPhoto;
	private Double price;	
	private String dizhi;
	
	
	
}
