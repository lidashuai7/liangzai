package com.cy.pj.entity;

public class tb_house {

	//房屋id
	private Integer houseId;
	//房屋名称
	private String houseName;
	//所在城市
	private String houseCity;
	//所在地点
	private String houseAddress;
	//主人电话
	private Integer masterPhone;
	//出租方式
	private Integer leaseWay;
	//房屋类型
	private String houseClass;
	//房屋面积
	private Integer floorSpace;
	//户型
	private String houseType;
	//床型
	private String bedType;
	//宜住人数
	private Integer peopleNumber;
	//同类房屋数量
	private Integer houseNumber;
	//是否同住
	private Integer togetherStatus;
	//房屋特色
	private String houseFeature;
	//交通位置
	private String position;
	//周边介绍
	private String ambitus;
	//房屋上架下架
	private Integer status;

	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getAmbitus() {
		return ambitus;
	}
	public void setAmbitus(String ambitus) {
		this.ambitus = ambitus;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public tb_house() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getHouseId() {
		return houseId;
	}
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public String getHouseCity() {
		return houseCity;
	}
	public void setHouseCity(String houseCity) {
		this.houseCity = houseCity;
	}
	public String getHouseAddress() {
		return houseAddress;
	}
	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}
	public Integer getMasterPhone() {
		return masterPhone;
	}
	public void setMasterPhone(Integer masterPhone) {
		this.masterPhone = masterPhone;
	}
	public Integer getLeaseWay() {
		return leaseWay;
	}
	public void setLeaseWay(Integer leaseWay) {
		this.leaseWay = leaseWay;
	}
	public String getHouseClass() {
		return houseClass;
	}
	public void setHouseClass(String houseClass) {
		this.houseClass = houseClass;
	}
	public Integer getFloorSpace() {
		return floorSpace;
	}
	public void setFloorSpace(Integer floorSpace) {
		this.floorSpace = floorSpace;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	public Integer getPeopleNumber() {
		return peopleNumber;
	}
	public void setPeopleNumber(Integer peopleNumber) {
		this.peopleNumber = peopleNumber;
	}
	public Integer getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}
	public Integer getTogetherStatus() {
		return togetherStatus;
	}
	public void setTogetherStatus(Integer togetherStatus) {
		this.togetherStatus = togetherStatus;
	}
	public String getHouseFeature() {
		return houseFeature;
	}
	public void setHouseFeature(String houseFeature) {
		this.houseFeature = houseFeature;
	}
	public tb_house(Integer houseId, String houseName, String houseCity, String houseAddress, Integer masterPhone,
			Integer leaseWay, String houseClass, Integer floorSpace, String houseType, String bedType,
			Integer peopleNumber, Integer houseNumber, Integer togetherStatus, String houseFeature, String position,
			String ambitus, Integer status) {
		super();
		this.houseId = houseId;
		this.houseName = houseName;
		this.houseCity = houseCity;
		this.houseAddress = houseAddress;
		this.masterPhone = masterPhone;
		this.leaseWay = leaseWay;
		this.houseClass = houseClass;
		this.floorSpace = floorSpace;
		this.houseType = houseType;
		this.bedType = bedType;
		this.peopleNumber = peopleNumber;
		this.houseNumber = houseNumber;
		this.togetherStatus = togetherStatus;
		this.houseFeature = houseFeature;
		this.position = position;
		this.ambitus = ambitus;
		this.status = status;
	}
	@Override
	public String toString() {
		return "tb_house [houseId=" + houseId + ", houseName=" + houseName + ", houseCity=" + houseCity
				+ ", houseAddress=" + houseAddress + ", masterPhone=" + masterPhone + ", leaseWay=" + leaseWay
				+ ", houseClass=" + houseClass + ", floorSpace=" + floorSpace + ", houseType=" + houseType
				+ ", bedType=" + bedType + ", peopleNumber=" + peopleNumber + ", houseNumber=" + houseNumber
				+ ", togetherStatus=" + togetherStatus + ", houseFeature=" + houseFeature + ", position=" + position
				+ ", ambitus=" + ambitus + ", status=" + status + "]";
	}

	
	
	
}
