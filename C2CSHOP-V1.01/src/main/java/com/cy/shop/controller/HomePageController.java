package com.cy.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.shop.common.vo.JsonResult;
import com.cy.shop.service.HomePageService;
import com.cy.shop.vo.HomePageVo;
import com.cy.shop.vo.LoginUserVo;

@Controller
@RequestMapping("/")
public class HomePageController {

	@Autowired
	private HomePageService homePageService;
	
	
	@RequestMapping("homepage")
	public String homePage(ModelMap map,LoginUserVo user) {
		map.addAttribute("username", user.getUsername());
		map.addAttribute("userId", user.getId());
		return "shop/homepage";
	}
	
	@RequestMapping("findObject")
	@ResponseBody
	public JsonResult findObject() {
		List<HomePageVo> list = homePageService.findHomePageObject();
		return new JsonResult(list);
	}
	
	@RequestMapping("loginpage")
	public String loginPage() {
		return "shop/login";
	}
	
	@RequestMapping("register")
	public String Register() {
		return "shop/regist";
	}
	
	
	
	
	
}
