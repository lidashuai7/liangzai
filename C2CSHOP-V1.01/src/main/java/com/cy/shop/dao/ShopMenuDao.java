package com.cy.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.shop.common.vo.Node;
import com.cy.shop.entity.ShopMenu;

@Mapper
public interface ShopMenuDao {
	/**1.查询所有菜单信息*/
	List<Map<String,Object>> findObjects();

	/**2.根据菜单id统计子菜单的个数*/
	int getChildCount(Integer id);

	/**3.根据id 删除菜单*/
	int deleteObject(Integer id);

	/**4.获取数据库对应的菜单表中的所有菜单信息*/
	List<Node> findZtreeMenuNodes();

	/**5.新增菜单*/
	int insertObject(ShopMenu entity);

	/**6.修改菜单*/
	int updateObject(ShopMenu entity);
	
	/**7.查询授权标识*/
	List<String> findPermissions(@Param("menuIds")Integer...menuIds);

}
