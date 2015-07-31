package com.yd.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.yd.util.JsonUtil;


@Namespace(value = "/admin")
@ParentPackage("default")
public class UserAction extends BaseAction{

	private static final long serialVersionUID = 358047072605107300L;
	
	/**
	 * 通过用户的openID判断用户是否注册
	 * 
	 * 
	 *@author 王海涛
	 *@date 2015年7月30日
	 *@return void
	 */
	@Action (value = "checkIsRegist")
	public void checkIsRegist(){
		try {
			HttpServletRequest request = this.getHttpServletRequest();
			String openID = request.getParameter("openID");
			int isRegist = 0;
			if(!StringUtils.isBlank(openID)){
				isRegist = this.getUserService().checkIsRegist(openID);
					//当用户已经注册 返回msg 1 当用户未注册 返回msg 0
					//success表示查询是否成功 只要查询成功 无论是否注册均返回true
					JsonUtil.outJson("{success:true,msg:'"+isRegist+"'}");
			}else
				JsonUtil.outJson("{success:false,msg:'未获取到openID'}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JsonUtil.outJson("{success:false,msg:'验证异常'}");
		}
		
	}
	
	/**
	 * 用户注册
	 * 
	 * 
	 *@author 王海涛
	 *@date 2015年7月30日
	 *@return void
	 */
	@Action (value="registAction")
	public void registAction(){
		HttpServletRequest request = this.getHttpServletRequest();
		
	}
		
}
