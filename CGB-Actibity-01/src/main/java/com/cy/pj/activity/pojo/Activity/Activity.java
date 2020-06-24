package com.cy.pj.activity.pojo.Activity;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Activity {
		private Integer id;
		private String title;
		private String category;
		@JsonFormat(pattern = "yyyy/MM/dd HH:mm")
		private Date startTime;
		@JsonFormat(pattern = "yyyy/MM/dd HH:mm")
		private Date endTime;
		private String remark;
		private int state;
		private Date createdTime;
		private String createdUser;
	
}
