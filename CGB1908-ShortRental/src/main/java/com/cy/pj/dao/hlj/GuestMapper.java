package com.cy.pj.dao.hlj;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.vo.hlj.Guest;
import com.cy.pj.vo.hlj.Order;

@Mapper
public interface GuestMapper {
	public void addGuest(Guest g);
	public void insertGuest(Guest g);
	Guest selectByIdCard(String id);
	void updateByIdCard(@Param("name") String name,@Param("id") String id);
	Guest selectByIdCard1(String id);
	void updateByIdCard1(@Param("name") String name,@Param("id") String id);
	
	public void addOrder(Order order);
	void updateByHouseId(Integer houseId);
}
