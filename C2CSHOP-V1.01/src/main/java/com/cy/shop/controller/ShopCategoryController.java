package com.cy.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.shop.common.vo.JsonResult;
import com.cy.shop.entity.ShopCategory;
import com.cy.shop.service.ShopCategoryService;

@RestController
@RequestMapping("/category/")
public class ShopCategoryController {
	@Autowired
	private ShopCategoryService shopCategoryService;

	/**1.查询所有类目信息*/
	@RequestMapping("doFindObjects")
	public JsonResult doFindObjects(){
		return new JsonResult(shopCategoryService.findObjects());
	}

	/**2.根据id删除类目元素*/
	@RequestMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id){
		shopCategoryService.deleteObject(id);
		return new JsonResult("delete OK");
	}

	/**3.获取数据库对应的类目表中的所有类目信息*/
	@RequestMapping("doFindZtreeCategoryNodes")
	public JsonResult doFindZtreeMenuNodes(){
		return new JsonResult(
				shopCategoryService.findZtreeMenuNodes());
	}
	
	/**4.添加或者修改类目*/
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(ShopCategory entity){
		shopCategoryService.saveObject(entity);
		return new JsonResult("save ok");
	}
	
	/**5.修改类目*/
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(ShopCategory entity){
		shopCategoryService.updateObject(entity);
	    return new JsonResult("update ok");
	}
}
