package com.cy.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.shop.common.exception.ServiceException;
import com.cy.shop.dao.ShopUserDao;
import com.cy.shop.dao.ShopUserRoleDao;
import com.cy.shop.entity.ShopUser;
import com.cy.shop.service.ShopUserService;

//后台管理用户
@Service
public class ShopUserServiceImpl extends BasePageServiceImpl<ShopUser> implements ShopUserService {
	private ShopUserDao shopUserDao;
	public ShopUserServiceImpl(@Autowired ShopUserDao shopUserDao) {
		super(shopUserDao);
		this.shopUserDao=shopUserDao;
	}
	@Autowired
	private ShopUserRoleDao shopUserRoleDao;

	//1.通过id查询用户信息，-->修改
	@Override
	public Map<String, Object> findObjectById(Integer id) {
		//1.参数校验
		if(id==null||id<1) {
			throw new IllegalArgumentException("id值无效");
		}
		//2.业务查询
		ShopUser user=shopUserDao.findObjectById(id);
		if(user==null) {
			throw new ServiceException("此用户已经不存在");
		}
		//3.查找用户对应的角色信息
		List<Integer> roleIds=
				shopUserRoleDao.findRoleIdsByUserId(id);
		//4.封装数据并返回
		Map<String,Object> map=new HashMap<>();
		map.put("user", user);
		map.put("roleIds", roleIds);
		return map;
	}

	//修改用户
	@Override
	public int updateObject(ShopUser entity, Integer[] roleIds) {
		//1.参数校验
		if(entity==null) {
			throw new IllegalArgumentException("保存对象不能为空");
		}
		if(StringUtils.isEmpty(entity.getUsername())) {
			throw new IllegalArgumentException("用户名不能为空");
		}
		//2.写用户信息
		int rows=shopUserDao.updateObject(entity);
		//3.写用户角色关系数据
		shopUserRoleDao.deleteObjectsByUserId(entity.getId());
		shopUserRoleDao.insertObjects(entity.getId(),roleIds);
		//4.返回结果
		return rows;
	}

	//添加保存用户信息
	@Override
	public int saveObject(ShopUser entity, Integer[] roleIds) {
		//1.参数校验
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getPassword()))
			throw new IllegalArgumentException("密码不能为空");
		//2.写用户信息
		String salt=UUID.randomUUID().toString();
		SimpleHash sh=new SimpleHash(
				"MD5",//algorithmName 加密算法 
				entity.getPassword(), //source 没加密的密码
				salt, //盐值
				1);//hashIterations
		entity.setSalt(salt);
		entity.setPassword(sh.toHex());
		int rows=shopUserDao.insertObject(entity);
		//3.写用户角色关系数据
		shopUserRoleDao.insertObjects(entity.getId(),roleIds);
		//4.返回结果
		return rows;
	}

	//禁用
	@Override
	public int validById(Integer id, Integer valid) {
		if(id==null||id<1)
			throw new IllegalArgumentException("id值不合法");
		if(valid!=1&&valid!=0)
			throw new IllegalArgumentException("状态值不正确");
		//..
		int rows=shopUserDao.validById(id, valid);
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		return rows;
	}

	//修改密码
	@Override
	public int updatePassword(String password, String newPassword, String cfgPassword) {
		//1.判定新密码与密码确认是否相同
		if(StringUtils.isEmpty(newPassword))
			throw new IllegalArgumentException("新密码不能为空");
		if(StringUtils.isEmpty(cfgPassword))
			throw new IllegalArgumentException("确认密码不能为空");
		if(!newPassword.equals(cfgPassword))
			throw new IllegalArgumentException("两次输入的密码不相等");
		//2.判定原密码是否正确
		if(StringUtils.isEmpty(password))
			throw new IllegalArgumentException("原密码不能为空");
		//获取登陆用户
		ShopUser user=(ShopUser)SecurityUtils.getSubject().getPrincipal();
		SimpleHash sh=new SimpleHash("MD5",
				password, user.getSalt(), 1);
		if(!user.getPassword().equals(sh.toHex()))
			throw new IllegalArgumentException("原密码不正确");
		//3.对新密码进行加密
		String salt=UUID.randomUUID().toString();
		sh=new SimpleHash("MD5",newPassword,salt, 1);
		//4.将新密码加密以后的结果更新到数据库
		int rows=shopUserDao.updatePassword(sh.toHex(), salt,user.getId());
		if(rows==0)
			throw new ServiceException("修改失败");
		return rows;

	}

}
