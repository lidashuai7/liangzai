package com.cy.pj.service.zsm;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cy.pj.entity.tb_house;
import com.cy.pj.vo.zsm.collectVo;
import com.cy.pj.vo.zsm.house_count;
import com.cy.pj.vo.zsm.house_de;
import com.cy.pj.vo.zsm.house_erji;
import com.cy.pj.vo.zsm.house_xq;
import com.cy.pj.vo.zsm.povo;
import com.cy.pj.vo.zsm.tb_collect;
import com.github.pagehelper.PageInfo;

public interface HouseCountService {
	List<house_count>queryHouseCount();
	
	List<house_de>queryhouse();
	
	List<house_de>queryhouse1();
	
	List<povo>querypost();

	PageInfo<house_erji> houseerji(Integer pageNum, Integer pageSize, String position, String housetype, Integer price1,
			Integer price2,String weizhi,Integer xu);

	house_xq queryhousexq(String houseid);
	

	tb_collect querycollect(String houseid);
	
	
	int insertCollect(tb_collect collect);
	

	PageInfo<collectVo> querycollecthousexq(Integer userid,Integer pagesize,Integer pagenum);
	
	int deletecollect(Integer collectId);

}
