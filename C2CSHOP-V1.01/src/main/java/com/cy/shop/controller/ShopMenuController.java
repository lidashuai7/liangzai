package com.cy.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.shop.common.vo.JsonResult;
import com.cy.shop.entity.ShopMenu;
import com.cy.shop.service.ShopMenuService;

@RestController
@RequestMapping("/menu/")
public class ShopMenuController {
	@Autowired
	private ShopMenuService shopMenuService;

	/**1.查询所有菜单信息*/
	@RequestMapping("doFindObjects")
	public JsonResult doFindObjects(){
		return new JsonResult(shopMenuService.findObjects());
	}

	/**2.根据id删除菜单元素*/
	@RequestMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id){
		shopMenuService.deleteObject(id);
		return new JsonResult("delete OK");
	}

	/**3.获取数据库对应的菜单表中的所有菜单信息*/
	@RequestMapping("doFindZtreeMenuNodes")
	public JsonResult doFindZtreeMenuNodes(){
		return new JsonResult(
				shopMenuService.findZtreeMenuNodes());
	}
	
	/**4.添加或者修改菜单*/
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(ShopMenu entity){
		shopMenuService.saveObject(entity);
		return new JsonResult("save ok");
	}
	
	/**5.修改菜单*/
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(ShopMenu entity){
	    shopMenuService.updateObject(entity);
	    return new JsonResult("update ok");
	}
}
