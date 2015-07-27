package com.yd.util;

import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonControlUtil {
	/**
	 * 
	 * 为对象添加一个属性(用于格式化成JSON)
	 * 
	 */
	public static JSONObject addAttribute(Object obj,Map<String,Object> attr){
		JSONObject jo = JSONObject.fromObject(obj);
		for(Entry<String, Object> entry: attr.entrySet()){
			jo.element(entry.getKey(), entry.getValue());
		}
		return jo;
	}
	/**
	 * 
	 * 为对象添加一个属性(用于格式化成JSON)
	 * 
	 */
	public static JSONObject addAttribute(Object obj,Map<String,Object> attr,JsonConfig jc){
		JSONObject jo = JSONObject.fromObject(obj,jc);
		for(Entry<String, Object> entry: attr.entrySet()){
			jo.element(entry.getKey(), entry.getValue(),jc);
		}
		return jo;
	}
}
