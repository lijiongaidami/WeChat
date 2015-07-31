package com.yd.action;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yd.common.constant.SystemConstant;
import com.yd.service.IBaseService;
import com.yd.service.IUserService;

/**
 * 系统逻辑转发处理基类
 */
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = -5538460968552645936L;

	protected final Logger log = Logger.getLogger(this.getClass());

	@Autowired
	public IBaseService baseService;
	
	@Autowired
	public IUserService userService;
	
	/**
	 * 取得Action上下文
	 * 
	 */
	public ActionContext getActionContext() {
		return ActionContext.getContext();
	}

	/**
	 * 取得HttpServletRequest
	 * 
	 */
	public HttpServletRequest getHttpServletRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 取得HttpSession
	 * 
	 */
	public HttpSession getHttpSession() {
		return this.getHttpServletRequest().getSession();
	}

	/**
	 * 取得HttpSession
	 * 
	 */
	public HttpSession getHttpSession(boolean b) {
		return this.getHttpServletRequest().getSession(b);
	}

	/**
	 * 取得Map类型的session
	 * 
	 */
	public Map<String, Object> getSession() {
		return this.getActionContext().getSession();
	}

	/**
	 * 取得HttpServletResponse
	 * 
	 */
	public HttpServletResponse getHttpServletResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 取得当前的语言类型
	 * 
	 */
	public Locale getContextLocale() {
		return this.getActionContext().getLocale();
	}


	/**
	 * 取得当前用户的登录IP
	 * 
	 */
	public String getCurrentUserIP() {
		HttpServletRequest request = this.getHttpServletRequest();
		return request.getRemoteAddr();
		
	}



	
	public void outWriteString(String str) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		try{
			response.setContentType("textml; charset=utf-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(str);
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch(IOException e){

		}
	}
	
	/**
	 * 得到当前登录人
	 */
	public String getCurrentUserOpenID() {
		HttpSession session = this.getHttpSession();
		return (String) session.getAttribute(SystemConstant.CURRENT_USER_OPEN_ID);
	}
	

	public IBaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
}
