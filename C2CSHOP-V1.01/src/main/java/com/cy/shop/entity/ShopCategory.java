package com.cy.shop.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ShopCategory implements Serializable {
	private static final long serialVersionUID = -295269770218282757L;
	private Integer id;//类目id
	private String name;//类目名称
	private Integer parentId;//上级类目id
	private Integer sort;//排序(序号)
	private String note;//备注
	private Date createdTime;//创建时间
	private Date modifiedTime;//修改时间
}
