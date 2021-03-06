package com.cy.pj.dao.zsm;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.entity.tb_userinfo;
import com.cy.pj.entity.user;

@Mapper
public interface UserMapper {

	/**
	 * 登录
	 * @param useraccount
	 * @param userpwd
	 * @return
	 */
	tb_userinfo loginUser(@Param("username") String username,@Param("pwd") String pwd);
	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	int insertUser(@Param("username") String username,@Param("pwd") String pwd);
	
	/**
	 * 鼠标失去焦点查看用户名是否存在
	 * @param useraccount
	 * @return
	 */
	tb_userinfo queryUser(@Param("username") String username);
	
}
