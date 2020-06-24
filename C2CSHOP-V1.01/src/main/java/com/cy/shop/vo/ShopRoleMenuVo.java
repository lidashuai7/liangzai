package com.cy.shop.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
@Data
public class ShopRoleMenuVo implements Serializable{
	private static final long serialVersionUID = 4449217379395664528L;
	private Integer id;//角色id
	private String name;//角色名称
	private String note;//角色备注
	private List<Integer> menuIds;//角色对应的菜单id
	
}