package com.cy.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 计划：所有涉及到页面返回的方法都定义在此Controller中
 * @author pc
 */
@Controller
@RequestMapping("/")
public class PageController {
	  @RequestMapping("doIndexUI")
	  public String doIndexUI() {
		  return "starter";
	  }
	  @RequestMapping("doPageUI")
	  public String doPageUI() {
		  return "common/page";
	  }
	  @RequestMapping("doLogin")
	  public String doLogin() {
		  return "login";
	  }
//	  @RequestMapping("/log/log_list")
//	  public String doLogUI() {
//		  System.out.println("doLogUI()");
//		  return "sys/log_list";
//	  }
//	  @RequestMapping("/menu/menu_list")
//	  public String doMenuUI() {
//		  System.out.println("==doMenuUI()==");
//		  return "sys/menu_list";
//	  }
	  
	  //rest风格(软件编码架构风格)的url定义
	  //语法格式:/{a}/{b}/...;其中{}中的内容可以理解为一个变量
	  //@PathVariable 注解可以描述方法参数，用于获取url中与方法参数相同的变量值
	  @RequestMapping("/{module}/{moduleUI}")
	  public String doModuleUI(@PathVariable String moduleUI) {
		  //System.out.println("==doModuleUI()==");
		  return "sys/"+moduleUI;
	  }
}









