package com.cy.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cy.shop.common.vo.CheckBox;
import com.cy.shop.entity.ShopRole;
import com.cy.shop.vo.ShopRoleMenuVo;

@Mapper
public interface ShopRoleDao extends BasePageDao<ShopRole>{
	
	/**
	 * 查询所有角色，在添加/编辑用户时使用
	 * @return 返回封装角色id和角色名称的vo对象
	 */
	@Select("select id,name from shop_roles")
	List<CheckBox> findObjects();
	
	/**
	 * 通过id删除角色
	 * @param id
	 * @return
	 */
	@Delete("delete from shop_roles where id=#{id}")
	int deleteObject(Integer id);
	/**
	 * 更新角色信息
	 * @param entity
	 * @return
	 */
	int updateObject(ShopRole entity);
	/**
	 * 写入角色信息
	 * @param entity
	 * @return
	 */
	int insertObject(ShopRole entity);
	/**
	 * 基于角色id查询角色以及对应的菜单信息
	 * @param id
	 * @return
	 */
	ShopRoleMenuVo findObjectById(Integer id);
	
}
