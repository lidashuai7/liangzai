package com.cy.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cy.shop.common.vo.Node;
import com.cy.shop.entity.ShopCategory;

@Mapper
public interface ShopCategoryDao {
	/**1.查询所有类目信息*/
	List<Map<String,Object>> findObjects();

	/**2.根据类目id统计子类目的个数*/
	int getChildCount(Integer id);
	
	/**3.根据id 删除类目*/
	int deleteObject(Integer id);
	
	/**4.获取数据库对应的类目表中的所有类目信息*/
	List<Node> findZtreeMenuNodes();
	
	/**5.新增类目*/
	int insertObject(ShopCategory entity);
	
	/**6.修改类目*/
	int updateObject(ShopCategory entity);
}
