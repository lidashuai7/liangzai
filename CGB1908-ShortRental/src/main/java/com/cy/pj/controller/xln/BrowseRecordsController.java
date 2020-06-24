package com.cy.pj.controller.xln;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.common.vo.JsonResult;
import com.cy.pj.service.xln.BrowseRecordsService;
import com.cy.pj.vo.xln.BrowseRecords;
import com.cy.pj.vo.xln.PageObject;

@Controller
@RequestMapping("/browse/")
public class BrowseRecordsController {
	@Autowired
	private BrowseRecordsService browseRecordsService;
	@RequestMapping("doBrowseRecordsList")
	@ResponseBody
	public JsonResult doBrowseList(String username,Integer pageCurrent) {
		PageObject<BrowseRecords> records = browseRecordsService.findBrowseRecords(username, pageCurrent);

		return new JsonResult(records);
	}
	@RequestMapping("addBrowseRecords")
	@ResponseBody
	public JsonResult doAddRecords(BrowseRecords browseRecords) {
		browseRecordsService.addBrowseRescords(browseRecords);
		return new JsonResult("records add success");
	}	
}
