package com.cy.shop.service;

import java.util.List;

import com.cy.shop.entity.ShopCart;
import com.cy.shop.vo.ShopCartVo;

public interface ShopCartService {
	List<ShopCart>selectObject(Integer id);
	List<ShopCartVo> doFindObjectById(Integer prodId,Integer payAmount,Integer userId);
}
