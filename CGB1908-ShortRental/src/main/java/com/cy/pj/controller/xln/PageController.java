package com.cy.pj.controller.xln;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {
	@RequestMapping("doOrderManage")
	public String doOrderManage() {	
		return "dingdang-fl";
	}
	
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "page";
	}
	
	@RequestMapping("doOrderDetails")
	public String doHomePage() {
		return "erji";
	}
	
	
	/*
	 * @RequestMapping("order/order_list") public String doLogUI() { return
	 * "order_list"; }
	 */
}
