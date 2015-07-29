package com.yd.exception;

/**
 *数据操作层异常处理类
 */
public class DaoException extends BaseException {

	private static final long serialVersionUID = -9006104640618533135L;

	public DaoException(String messageKey) {
		super.setMessageKey(messageKey);
	}

	public DaoException(String messageKey, Throwable t) {
		super.setMessageKey(messageKey);
		super.initCause(t);
	}

	public DaoException(Throwable t) {
		super.setMessageKey(t.getClass().getSimpleName());
		super.initCause(t);
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
