package com.yd.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.yd.common.constant.SystemConstant;
import com.yd.util.JsonUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户权限拦截器
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			HttpSession session = ServletActionContext.getRequest().getSession();
			if(session.getAttribute(SystemConstant.CURRENT_USER_AUTHORITY) == null) {
				return invocation.invoke();
			}
			
			Map<String,Integer> permissionMap = (Map<String,Integer>) session.getAttribute(SystemConstant.CURRENT_USER_AUTHORITY);
			
			ActionContext context = invocation.getInvocationContext();
			String invocationName = context.getName();
			
			if(permissionMap.containsKey(invocationName)) {  //判断权限里面是否存在该访问的类名
				if(permissionMap.get(invocationName) == 1) {	//1为由此权限
					 return invocation.invoke();
				} else {
					 JsonUtil.outJson("{success:false,msg:'你没有该操作权限！'}");
					 return null;
				}
			} else {
				return invocation.invoke();
			}
		} catch (Exception e) {
			return null;
		}
	}

}
