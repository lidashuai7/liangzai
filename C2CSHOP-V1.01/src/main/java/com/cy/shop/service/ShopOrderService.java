package com.cy.shop.service;

import com.cy.shop.vo.ShopOrderVo;

public interface ShopOrderService {
	ShopOrderVo doFindObjectById(Integer prodId,Integer payAmount,Integer orderId);
}
