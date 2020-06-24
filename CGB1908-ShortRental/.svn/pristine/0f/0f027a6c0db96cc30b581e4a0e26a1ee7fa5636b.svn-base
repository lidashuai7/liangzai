package com.cy.pj.service.xln.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cy.pj.dao.xln.BrowseRecordsDao;
import com.cy.pj.service.xln.BrowseRecordsService;
import com.cy.pj.vo.xln.BrowseRecords;
import com.cy.pj.vo.xln.PageObject;

import lombok.Data;
@Service
@Data
public class BrowseRecordsServiceImpl implements BrowseRecordsService{
	@Autowired
	private BrowseRecordsDao browseRecordsDao;
	@Override
	public PageObject<BrowseRecords> findBrowseRecords(String username, Integer pageCurrent) {
			  int rowCount=browseRecordsDao.getRowCount(username);
			  int pageSize=6;
			  int startIndex=(pageCurrent-1)*pageSize;
			  List<BrowseRecords> records = browseRecordsDao.findBrowseRecords(username, startIndex, pageSize);
			  //System.out.println(records);
			  PageObject<BrowseRecords> pageObject=new PageObject<BrowseRecords>(pageCurrent,pageSize,rowCount,records);
			  //5.返回封装结果。
			  return pageObject;
	}
	@Override
	public Integer addBrowseRescords(BrowseRecords browseRecords) {
		Integer row = browseRecordsDao.addBrowseRescords(browseRecords);
		return row;
	}
	
	

}
