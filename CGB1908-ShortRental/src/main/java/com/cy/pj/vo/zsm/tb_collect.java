package com.cy.pj.vo.zsm;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class tb_collect {

	private Integer collectId;
	
	private Integer userid;
	
	private String houseid;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")  
	private Date collectTime;

	public Integer getCollectId() {
		return collectId;
	}

	public void setCollectId(Integer collectId) {
		this.collectId = collectId;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getHouseid() {
		return houseid;
	}

	public void setHouseid(String houseid) {
		this.houseid = houseid;
	}

	public Date getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}

	public tb_collect(Integer collectId, Integer userid, String houseid, Date collectTime) {
		super();
		this.collectId = collectId;
		this.userid = userid;
		this.houseid = houseid;
		this.collectTime = collectTime;
	}

	public tb_collect() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "tb_collect [collectId=" + collectId + ", userid=" + userid + ", houseid=" + houseid + ", collectTime="
				+ collectTime + "]";
	}
	
	
}
