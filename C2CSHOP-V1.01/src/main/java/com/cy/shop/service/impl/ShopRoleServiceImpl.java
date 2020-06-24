package com.cy.shop.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.shop.common.exception.ServiceException;
import com.cy.shop.common.vo.CheckBox;
import com.cy.shop.dao.ShopRoleDao;
import com.cy.shop.dao.ShopRoleMenuDao;
import com.cy.shop.dao.ShopUserRoleDao;
import com.cy.shop.entity.ShopRole;
import com.cy.shop.service.ShopRoleService;
import com.cy.shop.vo.ShopRoleMenuVo;


@Service
public class ShopRoleServiceImpl extends BasePageServiceImpl<ShopRole> implements ShopRoleService {
	
	private ShopRoleDao shopRoleDao;
	public ShopRoleServiceImpl(@Autowired ShopRoleDao shopRoleDao) {
		super(shopRoleDao);//赋值给父类进行分页查询
		this.shopRoleDao=shopRoleDao;
	}
	
	@Autowired
	private ShopRoleMenuDao shopRoleMenuDao;//增删改角色时对角色菜单关系表的操作
	@Autowired
	private ShopUserRoleDao shopUserRoleDao;//删除角色后，在用户角色关系表中删除引用关系
	
	//查询所有角色，在添加/编辑用户时使用
	@Override
	public List<CheckBox> findObjects() {
		return shopRoleDao.findObjects();
	}
	
	//通过id查询出角色和菜单的VO对象，修改之前
	@Override
	public ShopRoleMenuVo findObjectById(Integer id) {
		if(id==null||id<1) {
			throw new IllegalArgumentException("id值不正确");
		}
		return shopRoleDao.findObjectById(id);
	}
	
	//修改角色
	@Override
	public int updateObject(ShopRole entity, Integer[] menuIds) {
		//1.参数校验
		if(entity==null) {
			throw new IllegalArgumentException("保存对象不能为空");
		}
		if(StringUtils.isEmpty(entity.getName())) {
			throw new IllegalArgumentException("角色名不能为空");
		}
		if(menuIds==null||menuIds.length==0) {
			throw new IllegalArgumentException("必须为角色分配权限");
		}
		//2.保存角色自身信息
		//2.1更新角色自身信息
		int rows=shopRoleDao.updateObject(entity);
		//2.2删除菜单角色关系数据
		shopRoleMenuDao.deleteObjectsByRoleId(entity.getId());
		//2.3插入菜单角色新的关系数据
		shopRoleMenuDao.insertObjects(entity.getId(), menuIds);
		//3.保存角色菜单关系数据
		return rows;
	}
	
	//添加保存角色
	@Override
	public int saveObject(ShopRole entity, Integer[] menuIds) {
		//1.参数校验
		if(entity==null) {
			throw new IllegalArgumentException("保存对象不能为空");
		}
		if(StringUtils.isEmpty(entity.getName())) {
			throw new IllegalArgumentException("角色名不能为空");
		}
		if(menuIds==null||menuIds.length==0) {
			throw new IllegalArgumentException("必须为角色分配权限");
		}
		//2.保存角色自身信息
		int rows=shopRoleDao.insertObject(entity);
		shopRoleMenuDao.insertObjects(entity.getId(), menuIds);
		//3.保存角色菜单关系数据
		return rows;
	}
	
	//通过id删除角色
	@Override
	public int deleteObject(Integer id) {
		if(id==null||id<1) {
			throw new IllegalArgumentException("id值无效");
		}
		shopRoleMenuDao.deleteObjectsByRoleId(id);//通过角色id删除角色和菜单的引用关系
		shopUserRoleDao.deleteObjectsByRoleId(id);//通过角色id删除角色和用户的引用关系
		int rows=shopRoleDao.deleteObject(id);//最后再删除角色
		if(rows==0) {
			throw new ServiceException("记录可能已经不存在");
		}
		return rows;
	}
	
	
}
