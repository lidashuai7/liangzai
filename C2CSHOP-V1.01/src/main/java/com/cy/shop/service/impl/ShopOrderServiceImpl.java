package com.cy.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.shop.common.exception.ServiceException;
import com.cy.shop.common.util.SendEmailUtil;
import com.cy.shop.dao.ShopOrderDao;
import com.cy.shop.dao.ShopProdDao;
import com.cy.shop.entity.ShopOrder;
import com.cy.shop.entity.ShopProd;
import com.cy.shop.service.ShopOrderService;
import com.cy.shop.vo.ShopOrderVo;

@Service
public class ShopOrderServiceImpl implements ShopOrderService {
	@Autowired
	private ShopProdDao shopProdDao;
	@Autowired
	private ShopOrderDao shopOrderDao;

	@Override
	public ShopOrderVo doFindObjectById(Integer prodId, Integer payAmount, Integer orderId) {
		if(orderId!=null) {
			ShopOrder forder = shopOrderDao.findObjectByOrderId(orderId);
			ShopProd prod = shopProdDao.findObjectById(forder.getProdId());
			ShopOrderVo order = new ShopOrderVo();
			order.setPayAmount(forder.getPayAmount());
			order.setProdId(prod.getId());
			order.setProdName(prod.getName());
			order.setProdPrice(prod.getPrice());
			order.setPhoto(prod.getMainPhoto());
			order.setProdUnit(prod.getUnit());
			order.setOrderId(orderId);
			
			String content="亲，您购买的"+prod.getName()+"，已成功支付，待商家发货";
			try {
				SendEmailUtil.sendEmail("742395105@qq.com", content, "订单支付成功");
			}  catch (Exception e) {
			}
			
			
			return order;
		}else {
			if (prodId == null || payAmount == null || prodId < 1 || payAmount < 1)
				throw new ServiceException("参数不合法");
			ShopProd prod = shopProdDao.findObjectById(prodId);
			if (prod == null)
				throw new ServiceException("没有找到对应记录");
			ShopOrderVo order = new ShopOrderVo();
			order.setPayAmount(payAmount);
			order.setProdId(prod.getId());
			order.setProdName(prod.getName());
			order.setProdPrice(prod.getPrice());
			order.setPhoto(prod.getMainPhoto());
			order.setProdUnit(prod.getUnit());
			int orderIdnew = (int) System.currentTimeMillis();
			ShopOrder shopOrder = new ShopOrder();
			shopOrder.setOrderId(orderIdnew);
			shopOrder.setPayAmount(payAmount);
			shopOrder.setProdId(prodId);
			shopOrder.setStatus(1);
			shopOrderDao.insertObject(shopOrder);
			order.setOrderId(orderIdnew);
			return order;
		}
	}

}
