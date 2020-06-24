package com.cy.pj.entity;

public class tb_account_number {

	//id
	private Integer id;
	//银行 卡
	private  String accountNumber;
	//个人账号或者企业账号
	private String accountType;
	//商户名称
	private String name;
	//开户银行
	private String bankNname;
	//开户省份
	private String bankPprovince;
	//开户城市
	private String bankCity;
	//开户支行
	private String bankBranch;
	//银行账号
	private String bankNumber;
	//公私类型
	private String numberType;
	//开户姓名
	private String ownerName;
	//按天结算，按月结算
	private String accountTime;
	//联系电话
	private String ownerPhone;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBankNname() {
		return bankNname;
	}
	public void setBankNname(String bankNname) {
		this.bankNname = bankNname;
	}
	public String getBankPprovince() {
		return bankPprovince;
	}
	public void setBankPprovince(String bankPprovince) {
		this.bankPprovince = bankPprovince;
	}
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	public String getNumberType() {
		return numberType;
	}
	public void setNumberType(String numberType) {
		this.numberType = numberType;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getAccountTime() {
		return accountTime;
	}
	public void setAccountTime(String accountTime) {
		this.accountTime = accountTime;
	}
	public String getOwnerPhone() {
		return ownerPhone;
	}
	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}
	public tb_account_number() {
		super();
		// TODO Auto-generated constructor stub
	}
	public tb_account_number(Integer id, String accountNumber, String accountType, String name, String bankNname,
			String bankPprovince, String bankCity, String bankBranch, String bankNumber, String numberType,
			String ownerName, String accountTime, String ownerPhone) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.name = name;
		this.bankNname = bankNname;
		this.bankPprovince = bankPprovince;
		this.bankCity = bankCity;
		this.bankBranch = bankBranch;
		this.bankNumber = bankNumber;
		this.numberType = numberType;
		this.ownerName = ownerName;
		this.accountTime = accountTime;
		this.ownerPhone = ownerPhone;
	}
	@Override
	public String toString() {
		return "tb_account_number [id=" + id + ", accountNumber=" + accountNumber + ", accountType=" + accountType
				+ ", name=" + name + ", bankNname=" + bankNname + ", bankPprovince=" + bankPprovince + ", bankCity="
				+ bankCity + ", bankBranch=" + bankBranch + ", bankNumber=" + bankNumber + ", numberType=" + numberType
				+ ", ownerName=" + ownerName + ", accountTime=" + accountTime + ", ownerPhone=" + ownerPhone + "]";
	}

	
	
}
