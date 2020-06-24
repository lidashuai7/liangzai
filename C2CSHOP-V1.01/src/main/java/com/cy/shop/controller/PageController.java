package com.cy.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	// rest风格 ，通用访问
	@RequestMapping("{module}/{page}")
	public String doModuleUI(@PathVariable String page,
							@PathVariable String module) {
		return "sys/"+page;
	}

	
	
	/**1.返回后台主页面*/
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "starter";
	}
	
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";
	}
	
	/**登录认证页面*/
	@RequestMapping("doLoginUI")
	public String doLoginUI() {
		return "login";
	}
}
