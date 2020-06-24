package com.cy.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.shop.common.exception.ServiceException;
import com.cy.shop.common.vo.Node;
import com.cy.shop.dao.ShopCategoryDao;
import com.cy.shop.entity.ShopCategory;
import com.cy.shop.service.ShopCategoryService;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
	@Autowired
	private ShopCategoryDao shopCategoryDao;
//	@Autowired
//	private ShopProdDao shopProdDao;
//	调用商品表Dao，通过类目id查询是否有对应的商品

	/**1.查询所有类目信息*/
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> list = shopCategoryDao.findObjects();
		if(list==null||list.size()==0) {
			throw new ServiceException("没有找到对应的类目信息");
		}
		return list;
	}

	/**2.基于id查询类目元素,并进行判定*/
	@Override
	public int deleteObject(Integer id) {
		//1.验证数据的合法性
		if(id==null||id<=0) {
			throw new IllegalArgumentException("请先选择");
		}
		//2.基于id进行子元素查询
		int count=shopCategoryDao.getChildCount(id);
		if(count>0) {
			throw new ServiceException("请先删除子类目");
		}
		//3.通过类目id判断该类目下是否有商品，若有商品抛出异常“请先删除对应商品”
		//待后续完成
		
		//4.删除类目元素
		int rows=shopCategoryDao.deleteObject(id);
		if(rows==0) {
			throw new ServiceException("此类目可能已经不存在");
		}
		//5.返回结果
		return rows;
	}

	/**3.获取数据库对应的类目表中的所有类目信息*/
	@Override
	public List<Node> findZtreeMenuNodes() {
		return shopCategoryDao.findZtreeMenuNodes();
	}

	/**4.添加保存类目*/
	@Override
	public int saveObject(ShopCategory entity) {
		//1.合法验证
		if(entity==null) {
			throw new ServiceException("保存对象不能为空");
		}
		if(StringUtils.isEmpty(entity.getName())) {
			throw new ServiceException("类目名不能为空");
		}
		int rows;
		//2.保存数据
		try{
			rows=shopCategoryDao.insertObject(entity);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("保存失败");
		}
		//3.返回数据
		return rows;
	}

	/**5.修改类目*/
	@Override
	public int updateObject(ShopCategory entity) {
		//1.验证参数有效性
		if(entity==null) {
			throw new IllegalArgumentException("保存对象不能为空");
		}
		if(StringUtils.isEmpty(entity.getName())) {
			throw new IllegalArgumentException("类目名不能为空");
		}
		//...
		//2.持久化数据到数据库
		int rows;
		try {
			rows=shopCategoryDao.updateObject(entity);
		}catch(Throwable e) {
			e.printStackTrace();
			//send mail and message to user
			throw new ServiceException(e.getMessage());
		}
		//3.返回结果
		return rows;
	}

}
