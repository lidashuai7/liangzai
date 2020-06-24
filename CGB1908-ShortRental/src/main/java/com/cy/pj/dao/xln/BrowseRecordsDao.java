package com.cy.pj.dao.xln;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.vo.xln.BrowseRecords;

@Mapper
public interface BrowseRecordsDao {
	Integer getRowCount(@Param("username") String username);
	List<BrowseRecords> findBrowseRecords(@Param("username")String username,
		      @Param("startIndex")Integer startIndex,
		      @Param("pageSize")Integer pageSize);
	Integer addBrowseRescords(@Param("browseRecords") BrowseRecords browseRecords);
}
