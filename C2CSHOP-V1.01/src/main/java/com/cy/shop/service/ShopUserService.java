package com.cy.shop.service;


import java.util.Map;

import com.cy.shop.entity.ShopUser;


public interface ShopUserService extends BasePageService<ShopUser> {
	/**
	 * 基于用户id查询:
	 * 1)用户信息
	 * 2)用户对应的部门信息
	 * 3)用户关联的角色信息
	 * @param id
	 * @return
	 */
	Map<String, Object> findObjectById(Integer id);
	
	int validById(Integer id,Integer valid);
	
	int saveObject(ShopUser entity,Integer[]roleIds);
	
	int updateObject(ShopUser entity,Integer[]roleIds);
	
	int updatePassword(String password,String newPassword,String cfgPassword);
}
