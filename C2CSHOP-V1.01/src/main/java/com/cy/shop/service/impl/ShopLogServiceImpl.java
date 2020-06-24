package com.cy.shop.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.shop.common.exception.ServiceException;
import com.cy.shop.dao.ShopLogDao;
import com.cy.shop.entity.ShopLog;
import com.cy.shop.service.ShopLogService;

@Service
public class ShopLogServiceImpl extends BasePageServiceImpl<ShopLog> implements ShopLogService {
	/**1.分页查询*/
	private ShopLogDao shopLogDao;
	public ShopLogServiceImpl(@Autowired ShopLogDao shopLogDao) {
		super(shopLogDao);//给抽象父类的BasePageDao赋值，执行查询
		this.shopLogDao=shopLogDao;//给本类中的shopLogDao赋值
	}

	/**2.通过id删除日志*/
	@Override
	public int deleteObjects(Integer... ids) {
		//1.判定参数合法性
		if(ids==null||ids.length==0) {
			throw new IllegalArgumentException("请选择一个");
		}
		//2.执行删除操作
		int rows;
		try{
			rows=shopLogDao.deleteObjects(ids);
		}catch(Throwable e){
			e.printStackTrace();
			//发出报警信息(例如给运维人员发短信)
			throw new ServiceException("系统故障，正在恢复中...");
		}
		//4.对结果进行验证
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		//5.返回结果
		return rows;
	}
	
	
	/**3.保存日志*/
	@Override
	public void saveObject(ShopLog entity) {
		shopLogDao.insertObject(entity);
	}


}
