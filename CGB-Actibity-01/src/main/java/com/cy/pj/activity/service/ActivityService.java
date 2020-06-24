package com.cy.pj.activity.service;

import java.util.List;

import com.cy.pj.activity.pojo.Activity.Activity;

public interface ActivityService {
		List<Activity>findActivitys();
		int findDelete(Integer id);
		int saveActivity(Activity entity);
		Activity findById(Integer id);
}
