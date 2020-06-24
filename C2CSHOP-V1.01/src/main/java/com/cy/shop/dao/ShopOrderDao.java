package com.cy.shop.dao;

import org.apache.ibatis.annotations.Mapper;

import com.cy.shop.entity.ShopOrder;
@Mapper
public interface ShopOrderDao {
	int insertObject(ShopOrder shopOrder);
	ShopOrder findObjectByOrderId(Integer OrderId);
}
