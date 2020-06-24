package com.cy.shop.service;

import com.alipay.api.AlipayApiException;
import com.cy.shop.entity.AlipayBean;

public interface PayService {
	String alipay(AlipayBean alipayBean) throws AlipayApiException;
}
