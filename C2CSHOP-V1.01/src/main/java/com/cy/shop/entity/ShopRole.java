package com.cy.shop.entity;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
@Data
public class ShopRole implements Serializable{
	private static final long serialVersionUID = 5444781487816683086L;
	private Integer id;//角色id
	private String name;//角色名称
	private String note;//备注
	private Date createdTime;//创建时间
	private Date modifiedTime;//修改时间
}
