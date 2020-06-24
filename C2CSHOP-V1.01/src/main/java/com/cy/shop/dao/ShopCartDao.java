package com.cy.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.shop.entity.ShopCart;

@Mapper
public interface ShopCartDao {
	List<ShopCart> seletObject(@Param("userId")Integer userId);
	int insertObject(ShopCart shopCart);
}
