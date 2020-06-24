package com.cy.shop.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.shop.common.vo.JsonResult;
import com.cy.shop.entity.ShopUser;
import com.cy.shop.service.ShopUserService;
//后台管理系统

@RestController
@RequestMapping("/user/")
public class ShopUserController {
	@Autowired
	private ShopUserService shopUserService;

	//通过id查询用户
	@GetMapping("doFindObjectById")
	public JsonResult doFindObjectById(Integer id) {
		return new JsonResult(shopUserService.findObjectById(id));
	}

	//禁用
	@RequestMapping("doValidById")
	public JsonResult doValidById(
			Integer id,Integer valid) {
		shopUserService.validById(id, valid);
		return new JsonResult("update ok");
	}

	//修改用户
	@PostMapping("doUpdateObject")
	public JsonResult doUpdateObject(
			ShopUser entity,Integer[] roleIds) {
		shopUserService.updateObject(entity, roleIds);
		return new JsonResult("update ok");
	}

	//添加保存用户
	@PostMapping("doSaveObject")
	public JsonResult doSaveObject(
			ShopUser entity,Integer[] roleIds) {
		shopUserService.saveObject(entity, roleIds);
		return new JsonResult("save ok");
	}

	//分页查询
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(
			String username,
			Integer pageCurrent) {
		return new JsonResult(
				shopUserService.findPageObjects(username,pageCurrent));
	}

	//登录
	@RequestMapping("doLoginAdmin")
	public JsonResult doLogin(
			String username,
			String password,
			boolean isRememberMe) {
		//1.获取Subject对象
		Subject subject=SecurityUtils.getSubject();
		//2.提交用户信息
		UsernamePasswordToken token=new UsernamePasswordToken();
		token.setUsername(username);
		token.setPassword(password.toCharArray());
		if(isRememberMe) {
			token.setRememberMe(true);
		}
		subject.login(token);//提交给SecurityManager
		return new JsonResult("login ok");
	}

	//修改密码
	@RequestMapping("doUpdatePassword")
	public JsonResult doUpdatePassword(
			String pwd,
			String newPwd,
			String cfgPwd) {
		shopUserService.updatePassword(pwd, newPwd, cfgPwd);
		return new JsonResult("update ok");
	}


}
