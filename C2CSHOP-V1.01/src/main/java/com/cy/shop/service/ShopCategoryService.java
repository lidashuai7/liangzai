package com.cy.shop.service;

import java.util.List;
import java.util.Map;

import com.cy.shop.common.vo.Node;
import com.cy.shop.entity.ShopCategory;

public interface ShopCategoryService {
	/**1.查询所有类目信息*/
	List<Map<String,Object>> findObjects();

	/**2.基于id查询类目元素,并进行判定*/
	int deleteObject(Integer id);
	
	/**3.获取数据库对应的类目表中的所有类目信息*/
	List<Node> findZtreeMenuNodes();
	
	/**4.添加保存类目*/
	int saveObject(ShopCategory entity);
	
	/**5.修改类目*/
	int updateObject(ShopCategory entity);
}
