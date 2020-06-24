package com.cy.pj.vo.xbc;

import java.util.Date;

import lombok.Data;

@Data
public class PassWord {
private Integer userid; 
private  String username;
private String  enterPassWord;
private String  confirmPassWord;
private  Date createDate;
private  Date modifyDate;
}
