package com.cy.shop.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JsonResult {
	// 下面的三个命名都是规范

	private int state = 1;// 1-ok  0-error
	private String message = "ok";
	private Object data;

	public JsonResult(String message) {
		this.message = message;
	}

	public JsonResult(Object data) {
		this.data = data;
	}

	public JsonResult(Throwable e) {
		this.message = e.getMessage();
		this.state = 0;
	}

}
