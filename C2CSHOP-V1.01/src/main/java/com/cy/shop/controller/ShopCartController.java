package com.cy.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cy.shop.common.vo.JsonResult;
import com.cy.shop.dao.ShopCartDao;
import com.cy.shop.dao.ShopUserDao;
import com.cy.shop.entity.ShopCart;
import com.cy.shop.service.ShopCartService;
import com.cy.shop.vo.ShopCartVo;

@Controller
@RequestMapping("/cart/")
public class ShopCartController {
	@Autowired
	private ShopCartService shopCartService;
	@Autowired
	private ShopUserDao shopUserDao;
	@Autowired
	private ShopCartDao shopCartDao;
	@RequestMapping("doShopCart")
	public ModelAndView doShopCart(
			Integer prodId,
			Integer payAmount,
			Integer userId,
			ModelAndView model) {
		model.addObject("prodId", prodId);
		model.addObject("payAmount", payAmount);
		model.addObject("userId", userId);
		String username = shopUserDao.findUsernameById(userId);
		model.addObject("username", username);
		model.setViewName("shop/shop_cart");
		return model;
	}
	//购物车页面加载完成后查询商品信息
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(Integer prodId,Integer payAmount,Integer userId) {
		List<ShopCartVo> result = shopCartService.doFindObjectById(prodId, payAmount,userId);
		ShopCart shopCart = new ShopCart();
		shopCart.setUserId(userId);
		shopCart.setPayAmount(payAmount);
		shopCart.setProdId(prodId);
		shopCartDao.insertObject(shopCart);
		return new JsonResult(result);
	}
	
}








