package com.cy.shop.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVo implements Serializable {

	private static final long serialVersionUID = 1989133227739120505L;
	
	private String username;
	private Integer id;
}
