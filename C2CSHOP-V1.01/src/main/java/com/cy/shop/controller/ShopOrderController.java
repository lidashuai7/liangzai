package com.cy.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cy.shop.common.vo.JsonResult;
import com.cy.shop.service.ShopOrderService;
import com.cy.shop.vo.ShopOrderVo;

@Controller
@RequestMapping("/order/")
public class ShopOrderController {
	@Autowired
	private ShopOrderService shopOrderService;
	
	@RequestMapping("doShopOrder")
	public ModelAndView doShopOrder(Integer prodId,Integer payAmount,Integer orderId,ModelAndView model) {
		model.addObject("prodId", prodId);
		model.addObject("payAmount", payAmount);
		model.addObject("orderId", orderId);
		model.setViewName("shop/shop_order");
		return model;
	}
	
	//订单页面加载完成后查询商品信息
		@RequestMapping("doFindObjects")
		@ResponseBody
		public JsonResult doFindObjects(Integer prodId,Integer payAmount,Integer orderId) {
			ShopOrderVo vo = shopOrderService.doFindObjectById(prodId, payAmount,orderId);
			return new JsonResult(vo);
		}
		@RequestMapping("paySuccess")
		public ModelAndView paySuccess(ModelAndView mv,Integer out_trade_no) {
			System.out.println(out_trade_no);
			Integer orderId=out_trade_no;
			mv.addObject("orderId", orderId);
			mv.setViewName("shop/shop_order");
			return mv;
		}
		
}






