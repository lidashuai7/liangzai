package com.cy.shop.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.shop.common.vo.JsonResult;
import com.cy.shop.common.vo.PageObject;
import com.cy.shop.entity.ShopCollector;
import com.cy.shop.service.ShopCollectorService;

;

@Controller
@RequestMapping("/collector/")
public class ShopCollectorController {
	
	@RequestMapping("collector")
	public String doSayHello() {
		return "shop/shop_collector";
	}

	@Autowired
	private ShopCollectorService shopCollectorService;
	@RequestMapping("doFindCollector")
	@ResponseBody
	public JsonResult dofindCollector(){
		return new JsonResult(shopCollectorService.findCollector());
	}


	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String name,Integer pageCurrent){
	 PageObject<ShopCollector> pageObject=
			 shopCollectorService.findPageObjects(name,pageCurrent);
	return new JsonResult(pageObject);
	}
	
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		shopCollectorService.deleteObject(id);
	return new JsonResult("delete Ok");
	}
	
}

