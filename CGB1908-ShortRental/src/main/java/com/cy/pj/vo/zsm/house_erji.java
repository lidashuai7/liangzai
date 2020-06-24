package com.cy.pj.vo.zsm;

public class house_erji {

	
	private String houseid;
	
	private String picturepath;
	
	private String housename;
	
	private String housecity;
	
	private String houseaddress;
	
	private Double baseprice;
	
	private String housetype;
	
	private String houseclass;
	
	private String bedtype;
	
	private String position;
	
	private String landlordpath;

	public String getHouseid() {
		return houseid;
	}

	public void setHouseid(String houseid) {
		this.houseid = houseid;
	}

	public String getPicturepath() {
		return picturepath;
	}

	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}

	public String getHousename() {
		return housename;
	}

	public void setHousename(String housename) {
		this.housename = housename;
	}

	public String getHousecity() {
		return housecity;
	}

	public void setHousecity(String housecity) {
		this.housecity = housecity;
	}

	public String getHouseaddress() {
		return houseaddress;
	}

	public void setHouseaddress(String houseaddress) {
		this.houseaddress = houseaddress;
	}

	public Double getBaseprice() {
		return baseprice;
	}

	public void setBaseprice(Double baseprice) {
		this.baseprice = baseprice;
	}

	public String getHousetype() {
		return housetype;
	}

	public void setHousetype(String housetype) {
		this.housetype = housetype;
	}

	public String getHouseclass() {
		return houseclass;
	}

	public void setHouseclass(String houseclass) {
		this.houseclass = houseclass;
	}

	public String getBedtype() {
		return bedtype;
	}

	public void setBedtype(String bedtype) {
		this.bedtype = bedtype;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLandlordpath() {
		return landlordpath;
	}

	public void setLandlordpath(String landlordpath) {
		this.landlordpath = landlordpath;
	}

	public house_erji(String houseid, String picturepath, String housename, String housecity, String houseaddress,
			Double baseprice, String housetype, String houseclass, String bedtype, String position,
			String landlordpath) {
		super();
		this.houseid = houseid;
		this.picturepath = picturepath;
		this.housename = housename;
		this.housecity = housecity;
		this.houseaddress = houseaddress;
		this.baseprice = baseprice;
		this.housetype = housetype;
		this.houseclass = houseclass;
		this.bedtype = bedtype;
		this.position = position;
		this.landlordpath = landlordpath;
	}

	public house_erji() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "house_erji [houseid=" + houseid + ", picturepath=" + picturepath + ", housename=" + housename
				+ ", housecity=" + housecity + ", houseaddress=" + houseaddress + ", baseprice=" + baseprice
				+ ", housetype=" + housetype + ", houseclass=" + houseclass + ", bedtype=" + bedtype + ", position="
				+ position + ", landlordpath=" + landlordpath + "]";
	}
	
	
}
