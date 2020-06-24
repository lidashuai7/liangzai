package com.cy.shop.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageObject<T> implements Serializable{
	private static final long serialVersionUID = -7768848413303590649L;
	
	public PageObject(Integer rowCount, Integer pageCurrent, Integer pageSize, List<T> records) {
		super();
		this.rowCount = rowCount;
		this.pageCurrent = pageCurrent;
		this.pageSize = pageSize;
		this.records = records;
		this.pageCount=(this.rowCount-1)/this.pageSize+1;
	}
	private Integer pageCurrent=1;//当前页的页码值
    private Integer pageSize=15;//页面大小
    private Integer rowCount=0;//总行数(通过查询获得)
    private Integer pageCount=0;//总页数(通过计算获得)
    private List<T> records;//当前页记录
}
