package com.uestc.util;

import net.sf.json.JSONObject;

public class JsonUtil {

	public static String getAttribute(String jsonString, String propertyName) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return jsonObject.get(propertyName).toString();
	}

	public static void main(String[] args) {
		String jsonString = "{\"access_token\":\"YhrLk-JlHBuxoiGQvIFAJzL06_MoZXspjV4ozKh2b26hPgZabEDctA0Vqej5AW6Um2ShCaAE9xsTcQKlnBOyTTk3xXpHH2KEelviM06Y8Ek\",\"expires_in\":7200}";
		System.out.println(getAttribute(jsonString, "access_token"));
	}

}
