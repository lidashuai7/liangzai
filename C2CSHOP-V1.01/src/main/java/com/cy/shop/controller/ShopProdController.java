package com.cy.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.shop.common.vo.JsonResult;
import com.cy.shop.common.vo.PageObject;
import com.cy.shop.dao.ShopUserDao;
import com.cy.shop.entity.ShopProd;
import com.cy.shop.service.ShopProdService;

@Controller
@RequestMapping("/prod/")

public class ShopProdController {
	@Autowired
	private ShopProdService shopProdService;
	@Autowired
	private ShopUserDao shopUserDao;
	//shop/single-product
	@RequestMapping("doFindObjectById")	
	@ResponseBody
	public JsonResult doFindObjectById(Integer prodId) {
		ShopProd result = shopProdService.doFindObjectById(prodId);
		return new JsonResult(result);
	}	//根据id查询所有商品信息，返回呈现页面的数据
	
	//后台管理系统的数据呈现
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String name,Integer pageCurrent) {
		PageObject<ShopProd> pageObjects = shopProdService.findPageObjects(name, pageCurrent);
		return new JsonResult(pageObjects);
	}
	//后台系统返回商品页面
	@RequestMapping("doProdListUI")
	public String doProdListUI() {
		return "sys/prod_list";
	}
	//后台系统返回商品编辑页面
	@RequestMapping("doProdEditUI")
	public String doProdEditUI() {
		return "sys/prod_edit";
	}
	//添加商品，从编辑页面接受数据
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(ShopProd entity,
			String file) {
		shopProdService.doSaveObject(entity,file);
		return new JsonResult("恭喜，添加成功！");
	}
	
	
	//访问商品详情页，需要传一个商品ID
	@RequestMapping("single-product")
	public String doModuleUI(Integer prodId,Integer userId,ModelMap model) {
		model.addAttribute("prodId", prodId);
		model.addAttribute("userId", userId);
		//根据id查找用户名
		String username = shopUserDao.findUsernameById(userId);
		model.addAttribute("username", username);
		return "shop/single-product";
	}
	
	@RequestMapping("doDeleteObjects")
	@ResponseBody
	public JsonResult doDeleteObjects(Integer [] ids) {
		shopProdService.doDeleteObjects(ids);
		return new JsonResult("删除成功！");
	}

	
}
