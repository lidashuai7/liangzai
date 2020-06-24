package com.cy.pj.controller.zsm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.pj.entity.tb_userinfo;
import com.cy.pj.entity.user;
import com.cy.pj.service.zsm.service.serviceImpl.UserServiceImpl;

@Controller
@RequestMapping("/zsm/userlogin")
public class UserController {

	@Autowired
	private UserServiceImpl biz;
	
	@RequestMapping("/login")
	public String login(String username,String pwd,HttpServletRequest req) {
	  HttpSession session=req.getSession();
	  tb_userinfo u=biz.loginUser(username, pwd);
	  System.out.println(u);
	  session.setAttribute("USER",u);
	  return "shouye";
	}
	
	@RequestMapping("/getSe")
	@ResponseBody
	public tb_userinfo getSe(HttpServletRequest req) {
		 HttpSession session=req.getSession();
		 System.out.println("getse:"+session);
		 tb_userinfo user= (tb_userinfo) session.getAttribute("USER");
		 System.out.println("我进来了！@！！！！！！！！！！！！！");
		 System.out.println(user.getUsername());
		 System.out.println(user.getUserid());
		return user;
	}
	
	@RequestMapping("/insertuser")
	public String insertlogin(String username,String pwd) {
		int i=biz.insertUser(username, pwd);
	return "shouye";
	}
	
	@RequestMapping("/queryuser")
	@ResponseBody
	public tb_userinfo queryuser(String username) {
		System.out.println(username);
		return biz.queryUser(username);
	}
	

}
