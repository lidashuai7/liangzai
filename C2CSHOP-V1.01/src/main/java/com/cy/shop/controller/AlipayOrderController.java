package com.cy.shop.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayApiException;
import com.cy.shop.entity.AlipayBean;
import com.cy.shop.service.PayService;

@Controller
@RequestMapping("alipayOrder")
public class AlipayOrderController {
	@Resource
	private PayService payService;
	@PostMapping("alipay")
	@ResponseBody
	public String alipay(String out_trade_no,String subject,String total_amount,String body) throws AlipayApiException {
		return payService.alipay(new AlipayBean()
				.setBody(body)
				.setOut_trade_no(out_trade_no)
				.setTotal_amount(new StringBuffer()
				.append(total_amount))
				.setSubject(subject));
	}
	
	@RequestMapping("/doPay")
	public ModelAndView doPay(String orderId,String prodName,Double count,ModelAndView mv) {
		mv.addObject("orderId", orderId);
		mv.addObject("prodName", prodName);
		mv.addObject("count", count);
		mv.setViewName("shop/shop_alipay");
		return mv;
	}
}






