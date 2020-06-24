package com.cy.pj.activity.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.pj.activity.pojo.Activity;
import com.cy.pj.activity.service.ActivityService;

@Controller
@RequestMapping("/activity/")
public class ActivityController {
	
     @Autowired
	 private ActivityService activityService;
     
     @RequestMapping("doFindById")
     @ResponseBody
     public Activity doFindById(Integer id) {
    	 return activityService.findById(id);
     }
//     @RequestMapping("doFindById")
//     public String doFindById(Integer id,Model model) {
//    	 Activity aty=activityService.findById(id);
//    	 model.addAttribute("aty", aty);
//    	 return "forward:doFindActivitys";
//     }
     
     @RequestMapping("doDeleteById")
     @ResponseBody
     public String doDeleteById(Integer id) {
    	 activityService.deleteById(id);
    	 return "delete ok"; 
     }
     
     @RequestMapping("doSaveActivity")
     @ResponseBody //spring mvc 会将返回的对象转换为json格式字符串
     public Activity doSaveActivity(Activity entity) {
    	 Activity aty=activityService.saveActivity(entity);
    	 return aty;
     }
     
     @RequestMapping("doActivityEditUI")
     public String doActivityEditUI() {
    	 return "activity_edit";
     }
     
     @RequestMapping("activity.html")
     public String doActivityUI() {
    	 //return "activity-ajax";//传统ajax方式的异步实现
    	 return "activity-jquery-ajax";//基于jquery中的ajax函数进行异步操作
     }
     @RequestMapping("jquery.html")
     public String doJQueryUI() {
    	 return "jquery-ajax-function";
     }
     
     @RequestMapping("doFindActivitys")
     @ResponseBody
     public List<Activity> doFindActivitys() {
    	 List<Activity> list=activityService.findActivitys();
    	 return list;
     }
      
}





