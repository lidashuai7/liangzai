package com.cy.shop.service.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.shop.dao.ShopMenuDao;
import com.cy.shop.dao.ShopRoleMenuDao;
import com.cy.shop.dao.ShopUserDao;
import com.cy.shop.dao.ShopUserRoleDao;
import com.cy.shop.entity.ShopUser;

/**用户认证*/
@Service
public class ShiroUserRealm extends AuthorizingRealm{
	@Autowired
	private ShopUserDao shopUserDao;
	@Autowired
	private ShopUserRoleDao shopUserRoleDao;
	@Autowired
	private ShopRoleMenuDao shopRoleMenuDao;
	@Autowired
	private ShopMenuDao shopMenuDao;
	
	//凭证匹配器
	@Override
		public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
			HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();//凭证匹配器
			cMatcher.setHashAlgorithmName("MD5");//加密算法
			cMatcher.setHashIterations(1);//加密次数
			super.setCredentialsMatcher(cMatcher);
		}
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShopUser user=(ShopUser)principals.getPrimaryPrincipal();//获取当前登录的用户
		Integer userId = user.getId();//用户id
		
		List<Integer> roleIds = shopUserRoleDao.findRoleIdsByUserId(userId);//通过用户id查找角色id
		if (roleIds==null||roleIds.size()==0) {
			throw new AuthorizationException();//无访问权限
		}
		
		Integer[] array= {};
		List<Integer> menuIds = shopRoleMenuDao.findMenuIdsByRoleId(roleIds.toArray(array));//通过角色id查找菜单id
		if (menuIds==null||menuIds.size()==0) {
			throw new AuthorizationException();
		}
		
		List<String> permissions = shopMenuDao.findPermissions(menuIds.toArray(array));//通过菜单id查询授权标识
		
		Set<String> set=new HashSet<>();
		for (String per : permissions) {
			if (!StringUtils.isEmpty(permissions)) {
				set.add(per);
			}
		}
		
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		info.setStringPermissions(set);
		return info;//返回给授权管理器
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken=(UsernamePasswordToken)token;
		String username = upToken.getUsername();//获取页面输入的用户名
		ShopUser user = shopUserDao.findUserByUsername(username);//从数据库查找用户
		if (user==null) {
			throw new UnknownAccountException();//判断用户是否存在
		}
		if (user.getValid()==0) {
			throw new LockedAccountException();//判断账户是否被禁用
		}
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());//获取盐值
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, this.getName());
		return info;//把从数据库查询出来封装好的用户信息，返回给认证管理器
	}
	
}
