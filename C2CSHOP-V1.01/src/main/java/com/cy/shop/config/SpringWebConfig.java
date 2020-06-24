package com.cy.shop.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.cy.shop.interceptor.ShopLoginInterceptor;

//@Configuration
public class SpringWebConfig //implements WebMvcConfigurer
{
	
	
	/**
	    * 配置spring-mvc拦截器
	 */
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> list = new ArrayList<>();
		//list.add("/order/doShopOrder");//订单页面
		//list.add("/cart/doShopCart");//购物车页面
		//list.add("/collector/collector");//收藏夹页面
 		registry.addInterceptor(new ShopLoginInterceptor()).addPathPatterns(list);
	}
	
	
	
	/**
	    * 过滤器注册器
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean  newFilterRegistrationBean() {
		//1.构建过滤器的注册器对象
		FilterRegistrationBean fBean = new FilterRegistrationBean();
		//2.注册过滤器对象
		DelegatingFilterProxy filter = new DelegatingFilterProxy("shiroFilterFactory");
		fBean.setFilter(filter);
		//3.进行过滤器配置
		//配置过滤器的生命周期管理(可选)由ServletContext对象负责
		//fBean.setEnabled(true);//默认值就是true
		fBean.addUrlPatterns("/*");
		return fBean;

	}
}
