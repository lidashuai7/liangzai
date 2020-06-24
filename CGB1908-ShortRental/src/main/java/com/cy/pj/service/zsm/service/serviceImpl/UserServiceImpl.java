package com.cy.pj.service.zsm.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.exception.ServiceException;
import com.cy.pj.dao.zsm.UserMapper;
import com.cy.pj.entity.tb_userinfo;
import com.cy.pj.entity.user;
import com.cy.pj.service.zsm.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper dao;
	
	@Override
	public tb_userinfo loginUser(String username, String pwd) {
		if(dao.loginUser(username, pwd)==null) {
			new ServiceException("账号密码不存在");
		}
		return dao.loginUser(username, pwd);
	}

	@Override
	public int insertUser(String username,String pwd) {
		if(dao.insertUser(username,pwd)!=0) {
			return 1;
		}else {
			return 0;
		}
		
			}

	@Override
	public tb_userinfo queryUser(String username) {
		return dao.queryUser(username);
	}

}
