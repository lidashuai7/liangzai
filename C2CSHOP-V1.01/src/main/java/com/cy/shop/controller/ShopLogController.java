package com.cy.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.shop.common.vo.JsonResult;
import com.cy.shop.common.vo.PageObject;
import com.cy.shop.entity.ShopLog;
import com.cy.shop.service.ShopLogService;

@RestController
@RequestMapping("/log/")
public class ShopLogController {
	@Autowired
	private ShopLogService shopLogService;

	/**1.分页查询*/
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(String username,Integer pageCurrent) {
		PageObject<ShopLog> pageObjects = shopLogService.findPageObjects(username, pageCurrent);
		return new JsonResult(pageObjects);//以Json格式返回
	}
	
	/**2.通过id删除日志*/
	@RequestMapping("doDeleteObjects")
	public JsonResult doDeleteObjects(Integer...ids){
		shopLogService.deleteObjects(ids);
		return new JsonResult("delete ok");
	}
}
