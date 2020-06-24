package com.cy.pj.activity.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.*;
import com.cy.pj.activity.pojo.*;
@Mapper
public interface ActivityDao {
	
	 @Select("select * from tb_activity where id=#{id}")
	 Activity findById(Integer id);
	
	 @Delete("delete from tb_activity where id=#{id}")
	 int deleteById(Integer id);
	
	 int insertActivity(Activity entity);
	 
	 int updateActivity(Activity entity);

	 @Select("select * from tb_activity order by createdTime desc")
	 List<Activity> findActivitys();
}
