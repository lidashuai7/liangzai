package com.cy.pj.service.zsm.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.dao.zsm.HouseCountMapper;
import com.cy.pj.entity.tb_house;
import com.cy.pj.service.zsm.HouseCountService;
import com.cy.pj.vo.zsm.collectVo;
import com.cy.pj.vo.zsm.house_count;
import com.cy.pj.vo.zsm.house_de;
import com.cy.pj.vo.zsm.house_erji;
import com.cy.pj.vo.zsm.house_xq;
import com.cy.pj.vo.zsm.povo;
import com.cy.pj.vo.zsm.tb_collect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class HouseCountServiceImpl implements HouseCountService {

	@Autowired
	private HouseCountMapper dao;
	
	@Override
	public List<house_count> queryHouseCount() {
		// TODO Auto-generated method stub
		return dao.queryHouseCount();
	}

	@Override
	public List<house_de> queryhouse() {
		// TODO Auto-generated method stub
		return dao.queryhouse();
	}

	@Override
	public List<house_de> queryhouse1() {
		// TODO Auto-generated method stub
		return dao.queryhouse1();
	}

	@Override
	public PageInfo<house_erji> houseerji(Integer pageNum,Integer pageSize,String position, 
			String housetype, Integer price1, Integer price2,String weizhi,Integer xu) {
		 PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<house_erji>(dao.houseerji(position, housetype, price1, price2,weizhi,xu));
	}

	@Override
	public List<povo> querypost() {
		// TODO Auto-generated method stub
		return dao.queryposition();
	}

	@Override
	public house_xq queryhousexq(String houseid) {
		// TODO Auto-generated method stub
		return dao.queryhousexq(houseid);
	}

	@Override
	public tb_collect querycollect(String houseid) {
		return dao.querycollect(houseid);
	}

	@Override
	public int insertCollect(tb_collect collect) {
		return dao.insertCollect(collect);	
	}

	@Override
	public PageInfo<collectVo> querycollecthousexq(Integer userid,Integer pagesize,Integer pagenum) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pagenum, pagesize);
		return new PageInfo<collectVo>(dao.querycollecthousexq(userid));
	}

	@Override
	public int deletecollect(Integer collectId) {
		// TODO Auto-generated method stub
		return dao.deletecollect(collectId);
	}

}
