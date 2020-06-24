package com.cy.shop.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.shop.common.vo.JsonResult;
import com.cy.shop.entity.ShopRole;
import com.cy.shop.service.ShopRoleService;

@RestController
@RequestMapping("/role/")
public class ShopRoleController {
	
	@Autowired
	private ShopRoleService shopRoleService;
	
	//加载角色信息
	@RequestMapping("doFindRoles")
	public JsonResult doFindRoles() {
		return new JsonResult(
		shopRoleService.findObjects());
	}
	
	//删除角色
	@RequestMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id) {
		shopRoleService.deleteObject(id);
		return new JsonResult("delete ok");
	}
	
	//修改角色
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(ShopRole entity,Integer[] menuIds) {
		shopRoleService.updateObject(entity,menuIds);
		return new JsonResult("update ok");
	}
	
	//添加保存角色
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(ShopRole entity,Integer[] menuIds) {
		shopRoleService.saveObject(entity,menuIds);
		return new JsonResult("save ok");
	}
	
	//通过id查询角色和菜单的VO，-->修改
	@RequestMapping("doFindObjectById")
	public JsonResult doFindObjectById(Integer id) {
		return new JsonResult(shopRoleService.findObjectById(id));
	}
	
	//角色列表分页查询
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(
		   String name,Integer pageCurrent) {
		return new JsonResult(
		shopRoleService.findPageObjects(name,pageCurrent));
	}
}
