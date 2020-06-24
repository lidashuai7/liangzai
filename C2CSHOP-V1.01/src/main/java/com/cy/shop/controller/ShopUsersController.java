package com.cy.shop.controller;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cy.shop.common.exception.ServiceException;
import com.cy.shop.common.vo.JsonResult;
import com.cy.shop.entity.ShopUsers;
import com.cy.shop.service.ShopUsersService;
import com.cy.shop.vo.LoginUserVo;

@RestController
@RequestMapping("user")
public class ShopUsersController {
	
	@Autowired
	private ShopUsersService shopUsersService;
	
	@RequestMapping("/doSaveUser")
	public JsonResult doSaveUser(ShopUsers shopUsers) {
		shopUsersService.saveObjet(shopUsers);
		return new JsonResult("save ok");
	}
	
	@RequestMapping("/doLogin")
	@ResponseBody
	public JsonResult doLogin(String username,String password) {
		//1.获取Subject对象
		//Subject subject = SecurityUtils.getSubject();
		//2.通过Subject提交用户信息,交给shiro框架进行认证操作
		//UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		//subject.login(token);
		if(username==null||username=="")
			throw new ServiceException("用户名错误");
		ShopUsers user = shopUsersService.findUserByUserName(username);
		SimpleHash SH = new SimpleHash("MD5",password,user.getSalt(),1);
		password = SH.toHex();
		
		if(!password.equals(user.getPassword())) {
			throw new ServiceException("密码错误");
		}
		
		LoginUserVo  u = new LoginUserVo(user.getUsername(),user.getId());
		return new JsonResult(u);
	}

}
