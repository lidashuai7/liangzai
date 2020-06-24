package com.cy.shop.service;


import java.util.List;

import com.cy.shop.common.vo.PageObject;
import com.cy.shop.entity.ShopCollector;

public interface ShopCollectorService {
	List<ShopCollector> findCollector();
	int deleteObject(Integer id);
		
	PageObject<ShopCollector> findPageObjects(
				 String name,
				 Integer pageCurrent);
	
	
}
