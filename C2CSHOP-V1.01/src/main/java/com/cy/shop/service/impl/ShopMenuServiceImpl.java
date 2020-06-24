package com.cy.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.shop.common.exception.ServiceException;
import com.cy.shop.common.vo.Node;
import com.cy.shop.dao.ShopMenuDao;
import com.cy.shop.dao.ShopRoleMenuDao;
import com.cy.shop.entity.ShopMenu;
import com.cy.shop.service.ShopMenuService;

@Service
public class ShopMenuServiceImpl implements ShopMenuService {
	@Autowired
	private ShopMenuDao shopMenuDao;
	@Autowired
	private ShopRoleMenuDao shopRoleMenuDao;

	/**1.查询所有菜单信息*/
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> list = shopMenuDao.findObjects();
		if(list==null||list.size()==0) {
			throw new ServiceException("没有找到对应的菜单信息");
		}
		return list;
	}

	/**2.基于id查询菜单元素,并进行判定*/
	@Override
	public int deleteObject(Integer id) {
		//1.验证数据的合法性
		if(id==null||id<=0) {
			throw new IllegalArgumentException("请先选择");
		}
		//2.基于id进行子元素查询
		int count=shopMenuDao.getChildCount(id);
		if(count>0) {
			throw new ServiceException("请先删除子菜单");
		}
		//3.删除菜单元素
		int rows=shopMenuDao.deleteObject(id);
		if(rows==0) {
			throw new ServiceException("此菜单可能已经不存在");
		}
		//4.删除角色,菜单关系数据
		shopRoleMenuDao.deleteObjectsByMenuId(id);
		//5.返回结果
		return rows;
	}

	/**3.获取数据库对应的菜单表中的所有菜单信息*/
	@Override
	public List<Node> findZtreeMenuNodes() {
		return shopMenuDao.findZtreeMenuNodes();
	}

	/**4.添加保存菜单*/
	@Override
	public int saveObject(ShopMenu entity) {
		//1.合法验证
		if(entity==null) {
			throw new ServiceException("保存对象不能为空");
		}
		if(StringUtils.isEmpty(entity.getName())) {
			throw new ServiceException("菜单名不能为空");
		}
		int rows;
		//2.保存数据
		try{
			rows=shopMenuDao.insertObject(entity);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("保存失败");
		}
		//3.返回数据
		return rows;
	}

	/**5.修改菜单*/
	@Override
	public int updateObject(ShopMenu entity) {
		//1.验证参数有效性
		if(entity==null) {
			throw new IllegalArgumentException("保存对象不能为空");
		}
		if(StringUtils.isEmpty(entity.getName())) {
			throw new IllegalArgumentException("菜单名不能为空");
		}
		//...
		//2.持久化数据到数据库
		int rows;
		try {
			rows=shopMenuDao.updateObject(entity);
		}catch(Throwable e) {
			e.printStackTrace();
			//send mail and message to user
			throw new ServiceException(e.getMessage());
		}
		//3.返回结果
		return rows;
	}

}
