package com.yd.action;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.uestc.util.HttpClientUtil;
import com.uestc.util.JsonUtil;
import com.uestc.util.Log;
import com.uestc.util.WeiXinConfig;

/**
 * 
 * @author justyoung
 *
 */
@Namespace(value = "/")
@ParentPackage("default")
public class RegisterAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1232869112699291933L;

	@Action(value = "authcode")
	public void getAuthCode() {
		HttpServletRequest request = this.getHttpServletRequest();
		HttpServletResponse response = this.getHttpServletResponse();
		// String phone = request.getParameter("phone");
		String openID = request.getParameter("openID");
		if (openID == null || openID.equals("")) {
			return;
		}
		try {
			response.getWriter().print(openID);
		} catch (IOException e) {
			Log.logException(e);
		}
	}

	@Action(value = "openID")
	public void getOpenID() {
		HttpServletRequest request = this.getHttpServletRequest();
		HttpServletResponse response = this.getHttpServletResponse();
		// 获取code值
		String code = request.getParameter("code");
		if (code == null || code.equals("")) {
			Log.logger.debug("没有获取到code值");
			return;
		}

		// 利用code值获取OpenID
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", WeiXinConfig.APP_ID);
		params.put("secret", WeiXinConfig.APP_SECRET);
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		String scheme = "https";
		String hostname = "api.weixin.qq.com";
		String path = "/sns/oauth2/access_token";
		String resultJson = null;
		try {
			resultJson = HttpClientUtil.sendGet(scheme, hostname, path, params);
		} catch (URISyntaxException | IOException e1) {
			Log.logException(e1);
		}
		if (resultJson == null || resultJson.equals(""))
			return;
		// 从返回的Json字符串中提取出OpenID
		Log.logger.debug("jsonString" + resultJson);
		String openID = JsonUtil.getAttribute(resultJson, "openid");
		if (openID == null || openID.equals("")) {
			return;
		}
		
		try {
			response.getWriter().print(openID);
		} catch (IOException e) {
			Log.logException(e);
		}
	}

}
