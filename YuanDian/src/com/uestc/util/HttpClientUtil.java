package com.uestc.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 利用HTTPClient发送HTTP或HTTPS请求
 * 
 * @author justyoung
 *
 */
public class HttpClientUtil {
	private static CloseableHttpClient httpClient;

	static {
		httpClient = HttpClients.createDefault();
	}

	public static String sendGet(String scheme, String hostname, String path, Map<String, String> params)
			throws URISyntaxException, ClientProtocolException, IOException {
		NameValuePair nameValuePair;
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
			paramList.add(nameValuePair);
		}
		URI uri = new URIBuilder().setScheme(scheme).setHost(hostname).setPath(path).addParameters(paramList).build();
		// System.out.println(uri);
		HttpGet httpGet = new HttpGet(uri);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity httpEntity = response.getEntity();
		if (httpEntity == null)
			return null;
		httpEntity = new BufferedHttpEntity(httpEntity);
		return EntityUtils.toString(httpEntity);
	}

	public static void main(String[] args) {
		String scheme = "https";
		String hostname = "api.weixin.qq.com";
		String path = "/sns/oauth2/access_token";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("code", "codeNum");
		try {
			sendGet(scheme, hostname, path, paramMap);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}

	}

}
