package com.cy.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.shop.dao.HomePageDao;
import com.cy.shop.service.HomePageService;
import com.cy.shop.vo.HomePageVo;

@Service
public class HomePageSerivceImpl implements HomePageService {

	@Autowired
	private HomePageDao homePageDao;
	
	
	@Override
	public List<HomePageVo> findHomePageObject(){
		List<HomePageVo> list = homePageDao.findHomePageObject();
		
		return list;
	}

}
