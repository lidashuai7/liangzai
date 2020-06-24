package com.cy.shop.service;

import java.util.List;
import java.util.Map;

import com.cy.shop.common.vo.Node;
import com.cy.shop.entity.ShopMenu;

public interface ShopMenuService {
	/**1.查询所有菜单信息*/
	List<Map<String,Object>> findObjects();

	/**2.基于id查询菜单元素,并进行判定*/
	int deleteObject(Integer id);
	
	/**3.获取数据库对应的菜单表中的所有菜单信息*/
	List<Node> findZtreeMenuNodes();
	
	/**4.添加保存菜单*/
	int saveObject(ShopMenu entity);
	
	/**5.修改菜单*/
	int updateObject(ShopMenu entity);
}
