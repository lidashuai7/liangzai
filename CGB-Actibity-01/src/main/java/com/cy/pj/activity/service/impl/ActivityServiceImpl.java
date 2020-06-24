package com.cy.pj.activity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.activity.dao.ActivityDao;
import com.cy.pj.activity.pojo.Activity.Activity;
import com.cy.pj.activity.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {
		@Autowired
		private ActivityDao activityDao;
		@Override
		public List<Activity> findActivitys() {
			List<Activity> list=activityDao.findActivitys();
		return list;
		}
		@Override
		public int findDelete(Integer id) {
			return activityDao.findDelete(id);
		}
		@Override
		public int saveActivity(Activity entity) {	
			if(entity.getId()==null) {
				return activityDao.insertActivity(entity);
			}else {
				return activityDao.updateActivity(entity);
			}
		}	
		@Override
		public Activity findById(Integer id) {
		return activityDao.findById(id);
		}
	}
