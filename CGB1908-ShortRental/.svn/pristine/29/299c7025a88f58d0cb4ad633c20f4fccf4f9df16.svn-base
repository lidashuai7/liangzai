package com.cy.pj.service.xln;

import org.apache.ibatis.annotations.Param;

import com.cy.pj.vo.xln.Order;
import com.cy.pj.vo.xln.PageObject;

public interface OrderManageService {
	PageObject<Order> findPageObjects(
			 String username,Integer tradeStatus);
	Integer cancelOrder(@Param("orderNum")String orderNum);

}
