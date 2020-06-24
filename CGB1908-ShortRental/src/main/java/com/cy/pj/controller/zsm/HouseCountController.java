package com.cy.pj.controller.zsm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cy.common.vo.JsonResult;
import com.cy.pj.entity.tb_house;
import com.cy.pj.entity.tb_userinfo;
import com.cy.pj.service.zsm.service.serviceImpl.HouseCountServiceImpl;
import com.cy.pj.vo.zsm.collectVo;
import com.cy.pj.vo.zsm.house_count;
import com.cy.pj.vo.zsm.house_de;
import com.cy.pj.vo.zsm.house_erji;
import com.cy.pj.vo.zsm.povo;
import com.cy.pj.vo.zsm.tb_collect;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/zsm/house")
public class HouseCountController {

	@Autowired
	private HouseCountServiceImpl biz;

	@RequestMapping("housecount")
	@ResponseBody
	private List<house_count> queryHouse() {
		System.out.println(biz.queryHouseCount());
		return biz.queryHouseCount();
	}

	@RequestMapping("housede")
	@ResponseBody
	private List<house_de> queryHouse1() {
		System.out.println(biz.queryhouse());
		return biz.queryhouse();
	}

	@RequestMapping("housede1")
	@ResponseBody
	private List<house_de> queryHouse2() {
		System.out.println(biz.queryhouse1());
		return biz.queryhouse1();
	}

	@RequestMapping("houseall")
	@ResponseBody
	public PageInfo<house_erji> qureyerji(Integer pagenum, Integer pagesize, String position, String housetype,
			Integer price1, Integer price2, String weizhi, Integer xu) {
		System.out.println(pagenum + "当前页");
		System.out.println(pagesize + "每页数量");
		System.out.println("位置信息" + weizhi);
		System.out.println("高低顺序" + xu);
		System.out.println("居室信息" + housetype);
		System.out.println("position" + position);
		PageInfo<house_erji> list = biz.houseerji(pagenum, pagesize, position, housetype, price1, price2, weizhi, xu);
		System.out.println(list);
		return list;
	}

	@RequestMapping("queryCollectVo")
	@ResponseBody
	public PageInfo<collectVo> queryCollectVo(Integer pagenum, Integer pagesize, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		tb_userinfo u = (tb_userinfo) session.getAttribute("USER");
		System.out.println(pagenum);
		System.out.println(pagesize);
		PageInfo<collectVo> list = biz.querycollecthousexq(u.getUserid(), pagesize, pagenum);
		System.out.println(list);
		return list;
	}

	@RequestMapping("deletecollect")
	@ResponseBody
	public JsonResult deletecollect(Integer collectId) {
		biz.deletecollect(collectId);
		return new JsonResult();
	}

	@RequestMapping("/goshouchang")
	public String gotosohuchang() {
		return "shoucang";
	}

	@RequestMapping("insertcollect")
	@ResponseBody
	public JsonResult insertcollect(String houseid,HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		tb_userinfo u = (tb_userinfo) session.getAttribute("USER");
		biz.insertCollect(new tb_collect(null, u.getUserid(), houseid, null));
	return new JsonResult();
	}
	
}
