package com.cy.shop.service;

import java.util.List;

import com.cy.shop.common.vo.CheckBox;
import com.cy.shop.entity.ShopRole;
import com.cy.shop.vo.ShopRoleMenuVo;


public interface ShopRoleService extends BasePageService<ShopRole> {

	/**
	 * 查询所有角色，在添加/编辑用户时使用
	 * @return 返回封装角色id和角色名称的vo对象
	 */
	List<CheckBox> findObjects();
	
	/**
	 * 基于角色id查询角色以及对应的菜单信息，修改角色信息时用到
	 * @param id
	 * @return
	 */
	ShopRoleMenuVo findObjectById(Integer id);
	
	/**
	 * 通过id删除角色
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	
	/**
	 * 更新角色以及角色对应的菜单信息
	 * @param entity
	 * @param menuIds
	 * @return
	 */
	int updateObject(ShopRole entity,Integer[]menuIds);
	/**
	 * 添加保存角色以及角色对应的菜单信息
	 * @param entity
	 * @param menuIds
	 * @return
	 */
	int saveObject(ShopRole entity,Integer[]menuIds);
}
