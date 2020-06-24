package com.cy.shop.service;


import com.cy.shop.entity.ShopLog;

public interface ShopLogService extends BasePageService<ShopLog>{
	
	/**1.分页查询*/
	//父类中实现
	
	/**2.通过id删除日志*/
	int deleteObjects(Integer...ids);
	
	/**3.保存日志*/
	void saveObject(ShopLog entity);
}
