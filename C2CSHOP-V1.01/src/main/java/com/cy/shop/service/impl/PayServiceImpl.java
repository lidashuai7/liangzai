package com.cy.shop.service.impl;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.cy.shop.common.util.AlipayUtil;
import com.cy.shop.entity.AlipayBean;
import com.cy.shop.service.PayService;
@Service
public class PayServiceImpl implements PayService{

	@Override
	public String alipay(AlipayBean alipayBean) throws AlipayApiException {
		return AlipayUtil.connect(alipayBean);
	}

}
