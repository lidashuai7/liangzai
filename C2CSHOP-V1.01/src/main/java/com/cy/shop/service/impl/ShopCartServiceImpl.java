package com.cy.shop.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.shop.common.exception.ServiceException;
import com.cy.shop.dao.ShopCartDao;
import com.cy.shop.dao.ShopProdDao;
import com.cy.shop.entity.ShopCart;
import com.cy.shop.service.ShopCartService;
import com.cy.shop.vo.ShopCartVo;
@Service
public class ShopCartServiceImpl implements ShopCartService{

	public List<ShopCart> selectObject(Integer id) {
		return null;
	}
	@Autowired
	private ShopProdDao shopProdDao;
	@Autowired
	private ShopCartDao shopCartDao;
	@Override
	public List<ShopCartVo> doFindObjectById(Integer prodId, Integer payAmount,Integer userId) {
		//模拟数据
//		userId=1;
		
		if(userId==null||userId<1)
			throw new ServiceException("亲，请先登录！");
		if(prodId==null||payAmount==null||prodId<1||payAmount<1)
			throw new ServiceException("参数不合法");
		
		//根据id查询用户的购物车信息（prodId,number）
		List<ShopCart> prodMsg = shopCartDao.seletObject(userId);
		//根据购物车的商品id查询商品，并将商品的amount改为number,返回值为List<ShopCartVo>
		List<ShopCartVo> list=new LinkedList<>();
		ShopCartVo newOne = shopProdDao.findCartById(prodId);
		newOne.setPayAmount(payAmount);
		list.add(newOne);
		for (ShopCart shopCart : prodMsg) {
			ShopCartVo findCartById = shopProdDao.findCartById(shopCart.getProdId());
			findCartById.setPayAmount(shopCart.getPayAmount());
			list.add(findCartById);
		}
		if(list.size()<1) 
			throw new ServiceException("没有找到对应记录");
		return list;
	}
 

}
