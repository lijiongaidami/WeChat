package com.yd.util;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

/**
 *json数据转化类
 */
public class JsonUtil {

	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 将对象等转换为json格式并输出
	 * 
	 */
	public static void outJson(Object obj) {
		try{
			String json = JSONObject.fromObject(obj).toString();
			getResponse().setContentType("text/html;charset=UTF-8");
			PrintWriter out = getResponse().getWriter();
			out.write(json);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 将对象等转换为json格式并输出，配置jsonConfig
	 * 
	 */
	public static void outJson(Object obj,JsonConfig jsonConfig) {
		try{
			String json = JSONObject.fromObject(obj,jsonConfig).toString();
			getResponse().setContentType("text/html;charset=UTF-8");
			PrintWriter out = getResponse().getWriter();
			out.write(json);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 将对象等转换为json格式并输出，配置jsonConfig
	 * 
	 */
	public static void outJson(Object obj,Map <String, Object> map ,JsonConfig jsonConfig) {
		try{
			String json = JsonControlUtil.addAttribute(obj, map,jsonConfig).toString();
			getResponse().setContentType("text/html;charset=UTF-8");
			PrintWriter out = getResponse().getWriter();
			out.write(json);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 将list.数组格式数据转换为json
	 * 
	 */
	public static void outJsonArray(Object obj) {
		try{
			String json = "";
			json += JSONArray.fromObject(obj).toString();
			getResponse().setContentType("text/html;charset=UTF-8");
			PrintWriter out = getResponse().getWriter();
			out.write(json);
		}
		catch(Exception e){

		}
	}
	
	/**
	 * 将list.数组格式数据转换为json,配置jsonConfig
	 * 
	 */
	public static void outJsonArray(Object obj,JsonConfig jsonConfig) {
		try{
			String json = "";
			json += JSONArray.fromObject(obj,jsonConfig).toString();
			getResponse().setContentType("text/html;charset=UTF-8");
			PrintWriter out = getResponse().getWriter();
			out.write(json);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
