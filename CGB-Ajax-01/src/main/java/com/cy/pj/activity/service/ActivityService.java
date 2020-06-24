package com.cy.pj.activity.service;
import java.util.*;
import com.cy.pj.activity.pojo.*;

public interface ActivityService {
	   
	   Activity findById(Integer id);
	   int deleteById(Integer id);
	   Activity saveActivity(Activity entity);
	   List<Activity> findActivitys();
}
