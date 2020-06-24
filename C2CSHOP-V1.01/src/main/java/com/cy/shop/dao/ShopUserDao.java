package com.cy.shop.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.shop.entity.ShopUser;
import com.cy.shop.entity.ShopUsers;


@Mapper
public interface ShopUserDao extends BasePageDao<ShopUser> {

	//通过id查询用户，-->修改
	ShopUser findObjectById(Integer id);

	//修改用户
	int updateObject(ShopUser entity);

	//插入用户
	int insertObject(ShopUser entity);

	//禁用启用
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid);

	//通过用户名查询用户（登录认证）
	ShopUser findUserByUsername(String username);

	//修改密码
	int updatePassword(
			@Param("password")String password,
			@Param("salt")String salt,
			@Param("id")Integer id);
	int saveObjet(ShopUsers user);
	/*查找用户*/
	@Select("select * from shop_users where username = #{username}")
	ShopUsers findUserByUserName(String username);
	@Select("select username from shop_users where id=#{id}")
	String findUsernameById(@Param("id") Integer id);
}
