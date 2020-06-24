package com.cy.pj.entity;

import java.util.Date;

public class tb_member {

	//会员主键
	private Integer id;
	//名字
	private String name;
	//身份证账号
	private String IDcard;
	//性别
	private Integer sex;
	//生日
	private Date birthday;
	//民族
	private String race;
	//户籍地
	private String address;
	//家庭地址
	private String home;
	//手机号
	private Integer number;
	//会员等级
	private String meember;
	//备注
	private String remarks;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIDcard() {
		return IDcard;
	}
	public void setIDcard(String iDcard) {
		IDcard = iDcard;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getMeember() {
		return meember;
	}
	public void setMeember(String meember) {
		this.meember = meember;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public tb_member(Integer id, String name, String iDcard, Integer sex, Date birthday, String race, String address,
			String home, Integer number, String meember, String remarks) {
		super();
		this.id = id;
		this.name = name;
		IDcard = iDcard;
		this.sex = sex;
		this.birthday = birthday;
		this.race = race;
		this.address = address;
		this.home = home;
		this.number = number;
		this.meember = meember;
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "tb_member [id=" + id + ", name=" + name + ", IDcard=" + IDcard + ", sex=" + sex + ", birthday="
				+ birthday + ", race=" + race + ", address=" + address + ", home=" + home + ", number=" + number
				+ ", meember=" + meember + ", remarks=" + remarks + "]";
	}
	public tb_member() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
