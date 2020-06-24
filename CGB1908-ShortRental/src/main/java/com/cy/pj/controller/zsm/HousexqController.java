package com.cy.pj.controller.zsm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cy.pj.service.zsm.service.serviceImpl.HouseCountServiceImpl;

@Controller
@RequestMapping("/zsm/housexq")
public class HousexqController {

	@Autowired
	private HouseCountServiceImpl biz;
	
	

	@RequestMapping("/xq")
	public String queryhousexq(Model model,String houseid,HttpServletRequest req) {
		HttpSession session=req.getSession(false);
		model.addAttribute("xq", biz.queryhousexq(houseid));
		System.out.println(biz.queryhousexq(houseid));
		System.out.println(session.getAttribute("USER"));
		model.addAttribute("user",session.getAttribute("USER"));
		return "xiangqing";
	}
	
	
}
