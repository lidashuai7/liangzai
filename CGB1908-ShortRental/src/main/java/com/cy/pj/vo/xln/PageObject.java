package com.cy.pj.vo.xln;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class PageObject<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 571738973670471245L;
	private Integer pageCurrent;
    private Integer pageSize;
    private Integer rowCount;
    private Integer pageCount;
	private Integer tradeStatus;
    private List<T> records;
	public PageObject(Integer tradeStatus,List<T> records) {
		super();
		this.tradeStatus = tradeStatus;
		this.records = records;
	}
	public PageObject(Integer pageCurrent, Integer pageSize, Integer rowCount, List<T> records) {
		super();
		this.pageCurrent = pageCurrent;
		this.pageSize = pageSize;
		this.rowCount = rowCount;
		this.records = records;
		this.pageCount=(rowCount-1)/pageSize+1;
	}
}
