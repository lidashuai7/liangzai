package com.cy.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.shop.entity.ShopProd;
import com.cy.shop.vo.ShopCartVo;
@Mapper
public interface ShopProdDao {
	
	ShopProd findObjectById(@Param("prodId")Integer prodId);
	
	String findNameById(@Param("id")Integer id);
	
	int getRowCount(@Param("name")String name);
	
	List<ShopProd> findPageObjects(
			@Param("name")String name,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	int insertObject(ShopProd entity);
	
	int deleteObjects(@Param("ids")Integer[] ids);
	
	ShopCartVo findCartById(@Param("prodId")Integer prodId);
}
