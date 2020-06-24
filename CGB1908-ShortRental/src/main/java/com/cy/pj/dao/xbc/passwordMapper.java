package com.cy.pj.dao.xbc;

import org.apache.ibatis.annotations.Mapper;

import com.cy.pj.vo.xbc.PassWord;
@Mapper
public interface passwordMapper {
	void updateByUsername(PassWord p);
	String selectByUsername(String username);
}
