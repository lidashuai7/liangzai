package com.cy.shop.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cy.shop.common.annotation.RequiredLog;
import com.cy.shop.common.util.IPUtils;
import com.cy.shop.entity.ShopLog;
import com.cy.shop.entity.ShopUser;
import com.cy.shop.service.ShopLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ShopLogAspect {
	@Autowired
	private ShopLogService shopLogService;

	@Around("@annotation(com.cy.shop.common.annotation.RequiredLog)")
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		long t1 = System.currentTimeMillis();//开始时间
		log.info("start:"+t1);
		Object result = jp.proceed();//执行目标方法
		long t2 = System.currentTimeMillis();//结束时间
		log.info("after:"+t2);
		saveLog(jp,(t2-t1));//保存日志信息到数据库
		return result;
	}

	private void saveLog(ProceedingJoinPoint jp, long time) throws JsonProcessingException {
		//1.获取用户行为日志
		//1.1获取方法所在的类及方法名
		//1)获取方法签名(封装了方法的相关信息)
		MethodSignature ms=(MethodSignature)jp.getSignature();
		//2)获取目标对象的字节码对象(通过它可以获取类全名)
		Class<?> targetCls=
				jp.getTarget().getClass();
		//3)获取目标类中的方法(通过其对象获取方法名)
		Method targetMethod=ms.getMethod();
		String clsMethod=
				targetCls.getName()+"."+targetMethod.getName();
		//1.2获取目标方法执行时的实际参数(通过连接点获取)
		Object[] args=jp.getArgs();
		ObjectMapper om=new ObjectMapper();//jackson
		String params=om.writeValueAsString(args);
		//1.3)获取目标方法上的注解及操作名
		RequiredLog rlog=
				targetMethod.getAnnotation(RequiredLog.class);
		String operation="operation";
		if(rlog!=null) {operation=rlog.value();}
		//1.4)获取登录用户(此信息在哪存着? session)
		ShopUser user=(ShopUser)
				SecurityUtils.getSubject().getPrincipal();
		//2.封装用户行为日志信息
		ShopLog log= new ShopLog()
				.setIp(IPUtils.getIpAddr())
				.setMethod(clsMethod)//类全名+方法名
		    	.setParams(params)//执行方法时传递的实际参数
		    	.setOperation(operation)
		    	.setUsername(user.getUsername())//登陆用户
		    	.setTime(time)
		    	.setCreatedTime(new Date());
		//3.将用户行为日志持久化到数据库
		shopLogService.saveObject(log);

	}

}
