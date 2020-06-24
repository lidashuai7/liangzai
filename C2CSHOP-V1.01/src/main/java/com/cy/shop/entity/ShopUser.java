package com.cy.shop.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class ShopUser implements Serializable{
	private static final long serialVersionUID = -7447006575577551655L;
	private Integer id;//用户id
	private String username;//用户名
	private String password;//密码
	private String salt;//盐值
	private String email;//邮箱
	private String phone;//手机号码
	private Integer valid=1;//账号有效性，1正常，0被禁用
	private Date createdTime;//创建时间
	private Date modifiedTime;//修改时间
}
