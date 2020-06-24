package com.cy.pj.controller.zsm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zsm/test")
public class TestController {

	@RequestMapping("gotoshouye")
	public String gotoIndex() {
		return "zhanghugl";
	}
	
	@RequestMapping("gotoshouye1")
	public String gotoIndex1() {
		return "dingdang-fl";
	}
	
	@RequestMapping("gotoshouye2")
	public String gotoIndex2() {
		return "erji";
	}
	
	@RequestMapping("gotoshouye3")
	public String gotoIndex3() {
		return "shoucang";
	}
	
	@RequestMapping("gotoshouye4")
	public String gotoIndex4() {
		return "shouye";
	}
	@RequestMapping("gotoshouye5")
	public String gotoIndex5() {
		return "xiadangye";
	}
	@RequestMapping("gotoshouye6")
	public String gotoIndex6() {
		return "xiangqing";
	}
	
}
