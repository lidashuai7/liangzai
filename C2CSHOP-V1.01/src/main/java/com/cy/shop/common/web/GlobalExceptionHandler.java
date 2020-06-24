package com.cy.shop.common.web;

import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cy.shop.common.exception.ServiceException;
import com.cy.shop.common.vo.JsonResult;

@RestControllerAdvice
public class GlobalExceptionHandler {
	// 处理Runtime类异常
	@ExceptionHandler(RuntimeException.class)
	// @ResponseBody
	public JsonResult doHandleRuntimeException(RuntimeException e) {
		e.printStackTrace();
		return new JsonResult(e);
	}

	// 捕获业务层的异常并包装
	@ExceptionHandler(ServiceException.class)
	public JsonResult doHandleServiceException(ServiceException e) {
		JsonResult result = new JsonResult(e.getMessage());
		result.setState(0);
		return result;
	}

	// 处理Shiro框架异常
	@ExceptionHandler(ShiroException.class)
	public JsonResult doHandleShiroException(ShiroException e) {
		JsonResult r = new JsonResult();
		r.setState(0);
		if (e instanceof UnknownAccountException) {
			r.setMessage("账户不存在");
		} else if (e instanceof LockedAccountException) {
			r.setMessage("账户已被禁用");
		} else if (e instanceof IncorrectCredentialsException) {
			r.setMessage("密码不正确");
		} else if (e instanceof AuthorizationException) {
			r.setMessage("没有此操作权限");
		} else {
			r.setMessage("系统维护中");
		}
		e.printStackTrace();
		return r;
	}
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public JsonResult doHandleSQLIntegrityConstraintViolationException(
			SQLIntegrityConstraintViolationException e) {
		JsonResult r = new JsonResult("用户已经存在");
		r.setState(0);
		return r;
	}
}
