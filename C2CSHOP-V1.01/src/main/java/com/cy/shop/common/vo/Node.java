package com.cy.shop.common.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 定义一个Node对象,借助此对象封装从数据库查询到的数据
 * @author Administrator
 */
@Data
public class Node implements Serializable {
	private static final long serialVersionUID = 4834651781629589388L;
	private Integer id;//菜单id
	private String name;//菜单名称
	private Integer parentId;//上级菜单id
}
