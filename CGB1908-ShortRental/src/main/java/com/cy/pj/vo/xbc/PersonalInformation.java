package com.cy.pj.vo.xbc;

import java.util.Date;

import lombok.Data;

@Data
public class PersonalInformation {
private Integer userid; 
private String username;
private  String  nickname;
private String name;
private  String gender;
private  String mailbox;
private  String phonenumber;
private  String sparephonenumber;
private  Date createDate;
private  Date modifyDate;
}
