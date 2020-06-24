package com.cy.pj.controller.xbc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.common.vo.JsonResult;
import com.cy.pj.entity.tb_userinfo;
import com.cy.pj.entity.user;
import com.cy.pj.service.xbc.InfromationService;
import com.cy.pj.vo.xbc.PersonalInformation;

@Controller
@RequestMapping("/")
public class InfromationController {
	@Autowired
	InfromationService service;
	
@RequestMapping("xbc/Infromation")
public String doInfromation(HttpServletRequest req) {
	HttpSession session = req.getSession(false);
	tb_userinfo user  = (tb_userinfo) session.getAttribute("USER");
	System.out.println(user);
	req.setAttribute("user", user);
	PersonalInformation p = service.selectPersonalInformation(user.getUsername());
	req.setAttribute("person", p);
	return "zhanghugl";
}
@RequestMapping("/saveuserinfo")
@ResponseBody
public JsonResult userinfo(PersonalInformation  e) {
	service.insert(e);
	return new JsonResult();
	
}
}
