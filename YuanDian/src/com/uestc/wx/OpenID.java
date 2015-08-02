package com.uestc.wx;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.sun.jndi.toolkit.url.Uri;
import com.uestc.util.JsonUtil;
import com.uestc.util.Log;
import com.uestc.util.WeiXinConfig;

public class OpenID {

	/**
	 * 获取用户在菜单栏中点击的链接
	 * 
	 * @param appId
	 * @param redirectUri
	 * @param state
	 * @return
	 */
	public static String getUri(String appId, String redirectUri, String state) {
		String uriString = String.format(
				"https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=%s",
				appId, redirectUri, state);
		return uriString;
	}

	/**
	 * 获取code之后，使用code来获取openID
	 * 
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String getOpenID(String appid, String secret, String code)
			throws URISyntaxException, ClientProtocolException, IOException {
		HttpClient httpClient = HttpClients.createDefault();
		URI uri = new URIBuilder().setScheme("https").setHost("api.weixin.qq.com").setPath("/sns/oauth2/access_token")
				.setParameter("appid", appid).setParameter("secret", secret).setParameter("code", code).build();
		HttpPost httpPost = new HttpPost(uri);
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity httpEntity = response.getEntity();
		httpEntity = new BufferedHttpEntity(httpEntity);
		String jsonString = EntityUtils.toString(httpEntity);
		Log.logger.debug("收到回复" + jsonString);
		String openid = JsonUtil.getAttribute(jsonString, "openid");
		Log.logger.debug("获取oppenID" + openid);
		return openid;
	}

	public static void main(String[] args) {
		try {
			String baseURI = getUri(WeiXinConfig.APP_ID, URLEncoder.encode("http://www.yuandianzone.com/YuanDianZone/OpenID", "utf-8"), "ok");
			System.out.println(baseURI);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
