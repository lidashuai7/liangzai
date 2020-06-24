package com.cy.pj.vo.xln;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class BrowseRecords implements Serializable{
	private static final long serialVersionUID = 1699382083989934372L;
	private Integer houseid;
	private String picturePath;
	private String houseName;
	private Double basePrice;
	private String houseType;
	private String houseAddress;
	private String username;
	
}
