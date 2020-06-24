package com.cy.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cy.shop.vo.HomePageVo;

@Mapper
public interface HomePageDao {
	/*获取主页信息*/
	List<HomePageVo> findHomePageObject();
}
