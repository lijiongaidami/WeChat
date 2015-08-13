package com.yd.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TreeMap;

import org.apache.http.client.ClientProtocolException;

import com.fr.base.core.json.JSONObject;

import open189.sign.ParamsSign;

import com.yd.util.HttpUtil;

public class SMSUtil {
	static String app_id = "217362570000038881";
	static String app_secret = "110021c94fa374aa68306634276ea9c4";
	static String access_token = "";
	
	@SuppressWarnings("unused")
	public static String createRandCode(){
		Random random = new Random();
		String result="";
		for(int i=0;i<6;i++){
			result+=random.nextInt(10);
		}
		return result;
	}
	
	private static String getAccess_Token() throws URISyntaxException, ClientProtocolException, IOException {
		String result="";
	    try {
			String postUrl = "https://oauth.api.189.cn/emp/oauth2/v2/access_token";
			String param = "grant_type=client_credentials&app_id="+ app_id + "&app_secret=" + app_secret;
			String resJson1 = HttpUtil.sendPost(postUrl, param);
			JSONObject json = new JSONObject(resJson1);
			result = json.get("access_token").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return result;
	}

	  @SuppressWarnings({ "unchecked", "rawtypes" })
	public static String send(String userPhone,String randcode) throws Exception {
	    access_token = getAccess_Token();
	    Date date = new Date();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String timestamp = dateFormat.format(date);
	    TreeMap paramsMap = new TreeMap();
	    paramsMap.put("app_id", app_id);
	    paramsMap.put("access_token", access_token);
	    paramsMap.put("timestamp", timestamp);

	    String getUrl = "http://api.189.cn/v2/dm/randcode/token";
	    String getParam = "app_id="
	        + app_id + "&access_token=" + access_token + "&timestamp="
	        + timestamp + "&sign="
	        + ParamsSign.value(paramsMap, app_secret);
	    String resJson = HttpUtil.sendGet(getUrl, getParam);
	    JSONObject json = new JSONObject(resJson);

	    TreeMap paramsMap1 = new TreeMap();
	    paramsMap1.put("app_id", app_id);
	    paramsMap1.put("access_token", access_token);
	    paramsMap1.put("token", json.get("token").toString());
	    paramsMap1.put("phone", userPhone);
	    paramsMap1.put("randcode", randcode);
	    paramsMap1.put("exp_time", "30");
	    paramsMap1.put("timestamp", timestamp);

	    String postUrl = "http://api.189.cn/v2/dm/randcode/sendSms";
	    String postEntity = "app_id=" + app_id + "&access_token="
	        + access_token + "&token=" + json.get("token") + "&phone="
	        + userPhone + "&randcode="
	        + randcode
	        + "&exp_time=" + "30" + "&timestamp=" + timestamp + "&sign="
	        + ParamsSign.value(paramsMap1, app_secret);
	    
	    String resJson1 = HttpUtil.sendPost(postUrl, postEntity);
	    return resJson1;
	  }
	  

}
