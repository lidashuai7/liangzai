package com.cy.shop.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)//set链式设置属性
public class ShopLog implements Serializable {
	private static final long serialVersionUID = 5021428399079719247L;
	private Integer id;//id
	private String username;//用户名
	private String operation;//用户操作
	private String method;//请求方法
	private String params;//请求参数
	private Long time;//执行时长(毫秒)
	private String ip;//IP地址
	private Date createdTime;//创建时间
}
