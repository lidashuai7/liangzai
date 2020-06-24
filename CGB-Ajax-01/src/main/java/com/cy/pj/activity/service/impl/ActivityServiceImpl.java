package com.cy.pj.activity.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.activity.dao.ActivityDao;
import com.cy.pj.activity.pojo.Activity;
import com.cy.pj.activity.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	private ActivityDao activityDao;
	
	@Override
	public Activity findById(Integer id) {
		return activityDao.findById(id);
	}
	
	@Override
	public int deleteById(Integer id) {
		//try{Thread.sleep(5000);}
		//catch (Exception e) {e.printStackTrace();}
		return activityDao.deleteById(id);
		
	}
	@Override
	public Activity saveActivity(Activity entity) {
		if(entity.getId()==null) {
		   entity.setCreatedTime(new Date());
		   System.out.println("insert.before="+entity);
		   activityDao.insertActivity(entity);//insert,setId
		   System.out.println("insert.after="+entity);//
		}else {
		   activityDao.updateActivity(entity);
		}
		return entity;
	}
	
	@Override
	public List<Activity> findActivitys() {
		//try{Thread.sleep(5000);}
		//catch (Exception e) {e.printStackTrace();}
		return activityDao.findActivitys();
	}

}




