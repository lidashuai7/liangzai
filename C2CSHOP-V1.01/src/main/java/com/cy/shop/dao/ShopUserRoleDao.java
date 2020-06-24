package com.cy.shop.dao;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ShopUserRoleDao {
	
	/**
	 * 选出用户对应的角色id，-->修改
	 * @param userId
	 * @return
	 */
	@Select("select role_id from shop_user_roles where user_id=#{userId}")
	List<Integer> findRoleIdsByUserId(Integer userId);
	
	/**
	 * 通过角色id删除用户和角色的引用关系，删除角色之后调用
	 * @param id
	 * @return
	 */
	@Delete("delete from shop_user_roles where role_id=#{id}")
	int deleteObjectsByRoleId(Integer id);
	
	/**
	 * 通过用户id删除用户和角色的引用关系，修改用户之前调用
	 * @param id
	 * @return
	 */
	@Delete("delete from shop_user_roles where user_id=#{id}")
	int deleteObjectsByUserId(Integer id);
	
	/**
	 * 通过用户id插入用户与角色的引用关系
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	int insertObjects(
			@Param("userId")Integer userId,
			@Param("roleIds")Integer[] roleIds);
}
