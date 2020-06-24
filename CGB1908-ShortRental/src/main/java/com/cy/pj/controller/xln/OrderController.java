package com.cy.pj.controller.xln;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.common.vo.JsonResult;
import com.cy.pj.entity.tb_userinfo;
import com.cy.pj.service.xln.OrderManageService;
import com.cy.pj.vo.xln.Order;
import com.cy.pj.vo.xln.PageObject;

@Controller
@RequestMapping("/order/")
public class OrderController {
	@Autowired
	private OrderManageService orderManageService;
	@RequestMapping("doOrderList")
	@ResponseBody
	public JsonResult doOrderList(String username,Integer tradeStatus) {
		 PageObject<Order> order = orderManageService.findPageObjects(username, tradeStatus);
		 System.out.println(username="xilima");
		 System.out.println(order);
		return new JsonResult(order);
	}
	@RequestMapping("doCancelOrder")
	@ResponseBody
	public JsonResult doCancelOrder(String orderNum) {
		orderManageService.cancelOrder(orderNum);
		return new JsonResult("Cancel Order Ok");
	}
}
