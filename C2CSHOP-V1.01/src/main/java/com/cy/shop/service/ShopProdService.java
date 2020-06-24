package com.cy.shop.service;

import com.cy.shop.common.vo.PageObject;
import com.cy.shop.entity.ShopProd;

public interface ShopProdService {
	ShopProd doFindObjectById(Integer prodId);
	//根据名字和当前页查询记录
	PageObject<ShopProd> findPageObjects(String name, Integer pageCurrent);
	int doSaveObject(ShopProd entity,String file);
	int doDeleteObjects(Integer [] ids);
}
