package com.cy.pj.dao.xln;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.cy.pj.vo.xln.Order;

@Mapper
public interface OrderDao {
	List<Order> allOrderFind(@Param("username")String username,
			  @Param("tradeStatus")Integer tradeStatus);
	@Update("update tb_house h,tb_order_item o set h.using_status=0,o.affirm_cancel=3,o.trade_status=3 where o.order_num=#{orderNum} and h.house_id=o.house_id")
	Integer cancelOrder(@Param("orderNum")String orderNum);
}
