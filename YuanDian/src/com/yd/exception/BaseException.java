package com.yd.exception;


/**
 *异常处理基类
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -3653870580234213024L;
	protected String messageKey;

	
	public BaseException() {
		super();
	}

	public BaseException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public BaseException(Throwable throwable) {
		super(throwable);
	}

	public BaseException(String messageKey) {
		super();
		this.messageKey = messageKey;
	}


	/**
	 * 取得异常信息key
	 * 
	 */
	public String getMessageKey() {
		return messageKey;
	}


	/**
	 * 设置异常信息key
	 * 
	 */
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

}
