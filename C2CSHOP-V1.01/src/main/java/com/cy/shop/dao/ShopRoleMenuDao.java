package com.cy.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShopRoleMenuDao {
	/**
	 * 基于菜单id执行删除操作
	 * @param id
	 * @return
	 */
	@Delete("delete from shop_role_menus where menu_id=#{id}")
	int deleteObjectsByMenuId(Integer id);
	
	/**
	 * 基于角色id执行删除操作
	 * @param id
	 * @return
	 */
	@Delete("delete from shop_role_menus where role_id=#{id}")
	int deleteObjectsByRoleId(Integer id);
	
	List<Integer> findMenuIdsByRoleId(@Param("roleIds")Integer...roleIds);
	/**
	 * 写入关系数据
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	int insertObjects(
			@Param("roleId")Integer roleId,
			@Param("menuIds")Integer[] menuIds);
}
