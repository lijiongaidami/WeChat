package com.yd.exception;

/**
 *业务处理层异常处理类
 */
public class ServiceException extends BaseException {

	private static final long serialVersionUID = -9006104640618533135L;

	public ServiceException(String messageKey) {
		super(messageKey);
	}

	public ServiceException(Throwable t) {
		if(t instanceof BaseException){
			super.setMessageKey(((BaseException) t).getMessageKey());
		}
		super.initCause(t);
	}

	public ServiceException(String messageKey, Throwable t) {
		super.setMessageKey(messageKey);
		super.initCause(t);
	}

	public ServiceException() {

	}

	public Throwable getOrignalException() {
		Throwable t = this.getCause();
		while(t.getCause() != null){
			t = t.getCause();
		}
		return t;
	}

	public String getOrignalMessageKey() {
		return this.getOrignalException().getClass().getSimpleName();
	}
}
