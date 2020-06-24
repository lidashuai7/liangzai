package com.cy.shop.service;

import org.apache.ibatis.annotations.Param;

import com.cy.shop.entity.ShopUsers;

public interface ShopUsersService {

	int saveObjet(@Param("user")ShopUsers user);
	ShopUsers findUserByUserName(String username);
}
