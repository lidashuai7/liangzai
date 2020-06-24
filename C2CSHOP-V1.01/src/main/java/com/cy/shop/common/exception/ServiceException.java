package com.cy.shop.common.exception;

public class ServiceException extends RuntimeException{
	private static final long serialVersionUID = -2686608105079885808L;
	public ServiceException() {
		super();
	}
	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public ServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
