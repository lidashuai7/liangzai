package com.cy.shop.service.impl;

import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.shop.common.exception.ServiceException;
import com.cy.shop.dao.ShopUserDao;
import com.cy.shop.entity.ShopUsers;
import com.cy.shop.service.ShopUsersService;

@Service
public class ShopUsersServiceImpl implements ShopUsersService {
	
	@Autowired
	private ShopUserDao shopUserDao;
	@Override
	public int saveObjet(ShopUsers entity) {
		//1.判断参数
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		ShopUsers findUserByUserName = findUserByUserName(entity.getUsername());
		if(findUserByUserName!=null)
			throw new ServiceException("用户名已经存在");
		if(StringUtils.isEmpty(entity.getPassword()))
			throw new IllegalArgumentException("密码不能为空");
		if(StringUtils.isEmpty(entity.getEmail()))	
			throw new IllegalArgumentException("邮箱不能为空");
		if(StringUtils.isEmpty(entity.getPhone()))
			throw new IllegalArgumentException("手机号码不能为空");
		//2.写入信息
		
		String salt = UUID.randomUUID().toString();
		SimpleHash sh = new SimpleHash("MD5",entity.getPassword(),salt,1);
		entity.setSalt(salt);
		entity.setPassword(sh.toHex());
		entity.setValid(1);
		int rows = shopUserDao.saveObjet(entity);
		return rows;
	}
	
	@Override
	public ShopUsers findUserByUserName(String username) {
		if(username==""||username==null)
			throw new IllegalArgumentException("请输入用户名");
		 
		return shopUserDao.findUserByUserName(username);
	}
}
