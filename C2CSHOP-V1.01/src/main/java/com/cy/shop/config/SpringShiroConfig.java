package com.cy.shop.config;

import java.util.LinkedHashMap;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.cy.shop.service.realm.ShiroUserRealm;

@Configuration
public class SpringShiroConfig {

	//安全管理器
		@Bean("securityManager")
		public SecurityManager newSecurityManager(
				@Autowired ShiroUserRealm realm,
				@Autowired CacheManager cacheManager,
				@Autowired CookieRememberMeManager cookieRememberMeManager,
				@Autowired SessionManager sessionManager) {
			DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
			sManager.setRealm(realm);
			sManager.setCacheManager(cacheManager);
			sManager.setRememberMeManager(cookieRememberMeManager);
			sManager.setSessionManager(sessionManager);
			return sManager;
		}

	@Bean("shiroFilterFactory")
	public ShiroFilterFactoryBean newShiroFilterFactoryBean(
			@Autowired SecurityManager securityManager) {
		ShiroFilterFactoryBean sfBean = new ShiroFilterFactoryBean();
		sfBean.setSecurityManager(securityManager);


		//定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
		map.put("/assets/**", "anon");
		map.put("/bower_components/**", "anon");
		map.put("/build/**", "anon");
		map.put("/dist/**", "anon");
		map.put("/plugins/**", "anon");
		map.put("/vendor/**", "anon");
		map.put("/cart/**", "anon");
		map.put("/collector/**", "anon");
		map.put("/doPageUI", "anon");
		map.put("/alipayOrder/**", "anon");
		
		//放行主页和列表
		map.put("/homepage", "anon");
		map.put("/findObject", "anon");
		map.put("/loginpage", "anon");
		map.put("/register", "anon");
		map.put("/user/**", "anon");
		map.put("/prod/**", "anon");
		map.put("/cart/**", "anon");
		map.put("/order/**", "anon");
		map.put("/user/doLoginAdmin", "anon");//对登录方法允许匿名访问
		map.put("/doLogout", "logout");//退出

		//假如没有认证请求先访问此认证的url
//		sfBean.setLoginUrl("/loginpage");


		//除了匿名访问的资源,其它都要认证("authc")后访问
//		map.put("/**","authc");
		map.put("/**", "user");//其他资源要认证
		sfBean.setFilterChainDefinitionMap(map);
		sfBean.setLoginUrl("/doLoginUI");//设置登录入口
		return sfBean;
	}
	
	//Bean对象的生命周期管理器
		@Bean("lifecycleBeanPostProcessor")
		public LifecycleBeanPostProcessor newLifecycleBeanPostProcessor() {
			return new LifecycleBeanPostProcessor();
		}

		//代理对象创建器
		@DependsOn("lifecycleBeanPostProcessor")
		@Bean
		public DefaultAdvisorAutoProxyCreator newDefaultAdvisorAutoProxyCreator() {
			return new DefaultAdvisorAutoProxyCreator();
		}

		//配置advisor对象
		@Bean
		public AuthorizationAttributeSourceAdvisor newAuthorizationAttributeSourceAdvisor(
				@Autowired SecurityManager securityManager) {
			AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
			return advisor;
		}

		//缓存
		@Bean
		public CacheManager newCacheManager() {
			return new MemoryConstrainedCacheManager();
		}

		//cookie
		public SimpleCookie newCookie() {
			SimpleCookie c=new SimpleCookie("rememberMe");
			c.setMaxAge(8*60*60);
			return c;
		}

		@Bean
		public CookieRememberMeManager newRememberMeManager() {
			CookieRememberMeManager cManager=new CookieRememberMeManager();
			cManager.setCookie(newCookie());
			return cManager;
		}

		//会话管理器
		@Bean
		public SessionManager newSessionManager() {
			DefaultWebSessionManager sManager=
					new DefaultWebSessionManager();
			sManager.setGlobalSessionTimeout(60*60*1000);
			return sManager;
		}
	
	

}
