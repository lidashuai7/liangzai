package com.cy.shop.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ShopMenu implements Serializable {
	private static final long serialVersionUID = -295269770218282757L;
	private Integer id;//菜单id
	private String name;//菜单名称
	private String url;//菜单url
	private Integer type=1;//菜单类型(两种:按钮,普通菜单)
	private Integer sort;//排序(序号)
	private String note;//备注
	private Integer parentId;//上级菜单id
	private String permission;//菜单对应的权限标识
	private Date createdTime;//创建时间
	private Date modifiedTime;//修改时间
}
