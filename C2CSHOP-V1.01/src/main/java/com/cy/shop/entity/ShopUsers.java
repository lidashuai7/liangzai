package com.cy.shop.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ShopUsers implements Serializable{
	
	private static final long serialVersionUID = -5731278072067672032L;
	private Integer id;
	private String username;
	private String password;
	private String phone;
	private String email;
	private String salt;
	private Integer valid;
	private Date createdTime ;
	private Date modifiedTime  ;
}
