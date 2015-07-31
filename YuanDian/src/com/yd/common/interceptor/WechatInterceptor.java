package com.yd.common.interceptor;


import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.yd.common.constant.SystemConstant;
import com.yd.entity.admin.User;

/**
 * 用户登录拦截器
 */
public class WechatInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 3513708880894509250L;
	private static Logger log = Logger.getLogger(WechatInterceptor.class);

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		ActionContext context = invocation.getInvocationContext();
		String invocationName = context.getName();
		log.warn("当前拦截的action是：" + invocationName);
		
		Set<String> exclude= this.excludeMethods;
		if (exclude.contains(invocationName)) {
			return invocation.invoke();
		}
		
		HttpServletRequest  requeset =  ServletActionContext.getRequest();
		HttpSession session = requeset.getSession();
		String openID = (String) session.getAttribute(SystemConstant.CURRENT_USER_OPEN_ID);
		if (!StringUtils.isBlank(openID)) {
			return invocation.invoke();
		} else {
			String headType = requeset.getHeader("X-Requested-With");
			if(headType !=null && headType.equalsIgnoreCase("XMLHttpRequest")){ 
				ServletActionContext.getResponse().sendError(999);   
			} 
		}
		return Action.LOGIN;
	}

}

