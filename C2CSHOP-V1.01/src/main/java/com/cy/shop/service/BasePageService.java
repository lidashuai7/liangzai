package com.cy.shop.service;

import com.cy.shop.common.vo.PageObject;

/**分页查询共性代码提取*/
public interface BasePageService<T> {
	/**
	 * 通过此方法实现分页查询操作
	 * @param name 基于条件查询时的参数名
	 * @param pageCurrent 当前的页码值
	 * @return 当前页记录+分页信息
	 */
	PageObject<T> findPageObjects(
			String name,
			Integer pageCurrent);
}
