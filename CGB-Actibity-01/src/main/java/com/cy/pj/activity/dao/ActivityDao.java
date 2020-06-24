package com.cy.pj.activity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cy.pj.activity.pojo.Activity.Activity;

@Mapper
public interface ActivityDao {
	
	@Select("select * from tb_activity order by createdTime desc")
		List<Activity>findActivitys();
	
	@Delete("delete from tb_activity where id=#{id}")
	int findDelete(Integer id);
	
	
	int insertActivity(Activity entity);
	
	
	int updateActivity(Activity entity);
	
	@Select("select * from tb_activity where id=#{id}")
	Activity findById(Integer id);
}
