package com.cy.shop.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.shop.entity.ShopLog;

@Mapper
public interface ShopLogDao extends BasePageDao<ShopLog>{
	
	/**1.分页查询*/
	//父类中实现
	
	
	/**2.通过id删除日志*/
	int deleteObjects(@Param("ids")Integer...ids);
	
	/**3.日志添加*/
	int insertObject(ShopLog entity);
}
