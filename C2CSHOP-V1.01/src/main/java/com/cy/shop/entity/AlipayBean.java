package com.cy.shop.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/*支付实体对象*/
@Data
@Accessors(chain = true)
public class AlipayBean {
	private String out_trade_no;
	private String subject;
	private StringBuffer total_amount;
	private String body;
	private String timeout_express = "10m";
	private String product_code = "FAST_INSTANT_TRADE_PAY";
}