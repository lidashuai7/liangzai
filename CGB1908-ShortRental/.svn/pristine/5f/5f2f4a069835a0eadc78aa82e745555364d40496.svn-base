package com.cy.pj.service.xln.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cy.pj.dao.xln.OrderDao;
import com.cy.pj.service.xln.OrderManageService;
import com.cy.pj.vo.xln.Order;
import com.cy.pj.vo.xln.PageObject;
import lombok.Data;

@Service
@Data
public  class OrderManageServiceImpl implements OrderManageService{
	@Autowired
	private OrderDao orderDao;

	@Override
	public PageObject<Order> findPageObjects(String username, Integer tradeStatus) {
			  List<Order> records=orderDao.allOrderFind(username, tradeStatus);
			  PageObject<Order> pageObject=new PageObject<Order>(tradeStatus,records);
			  return pageObject;
	}
	@Override
	public Integer cancelOrder(String orderNum) {
		Integer	rows = orderDao.cancelOrder(orderNum);
		//System.out.println(rows);
		return rows;
	}
	
}
