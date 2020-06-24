package com.cy.pj.controller.xbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.common.vo.JsonResult;
import com.cy.pj.dao.xbc.passwordMapper;
import com.cy.pj.service.xbc.passWordService;
import com.cy.pj.vo.xbc.PassWord;

@Controller
@RequestMapping("/")
public class passWordController {
@RequestMapping("xbc/passWord")
public String dopassWord() {
	return "zhanghugl";
}
@Autowired
passWordService wordService;
@Autowired
passwordMapper mapper;

@RequestMapping("/saveuser")
@ResponseBody
public JsonResult userinfo(PassWord p) {
	System.out.println(1242536);
	String str= mapper.selectByUsername(p.getUsername());
	if(str == null) return new JsonResult("賬號不存在");
	wordService.pws(p);
	return new JsonResult();
}
}
