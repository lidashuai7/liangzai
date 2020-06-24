package com.cy.pj.dao.xbc;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.vo.xbc.PersonalInformation;

@Mapper
public interface InfromationMapper {
	void insert(@Param("e") PersonalInformation e);

	void updateByUsername(@Param("e") PersonalInformation e);

	PersonalInformation selectByUsername(String username);

}
