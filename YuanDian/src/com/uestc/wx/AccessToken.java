package com.uestc.wx;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.uestc.util.JsonUtil;
import com.uestc.util.WeiXinConfig;

public class AccessToken {

	CloseableHttpClient httpClient;
	HttpGet httpGet;
	URI uri;

	public AccessToken() throws URISyntaxException {

		uri = new URIBuilder().setScheme("https").setHost("api.weixin.qq.com").setPath("/cgi-bin/token")
				.setParameter("grant_type", "client_credential").setParameter("appid", WeiXinConfig.APP_ID)
				.setParameter("secret", WeiXinConfig.APP_SECRET).build();
		httpClient = HttpClients.createDefault();
		httpGet = new HttpGet(uri);
	}

	public String getAccessToken() throws ClientProtocolException, IOException {
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity == null)
				return null;
			httpEntity = new BufferedHttpEntity(httpEntity);
			String returnString = EntityUtils.toString(httpEntity);
			// System.out.println(returnString);
			String accessToken = JsonUtil.getAttribute(returnString, "access_token");
			System.out.println(accessToken);
			return null;
		} finally {
			response.close();
		}
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		try {
			AccessToken at = new AccessToken();
			at.getAccessToken();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
	}

}
