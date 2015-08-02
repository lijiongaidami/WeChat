package com.uestc.wx;

import java.util.Date;

public class SubscribeEvent {

	static Date date = new Date();

	/**
	 * 处理用户的订阅事件
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String responseSubscribeEvent(String toUserName, String fromUserName) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append(String.format("<ToUserName><![CDATA[%s]]></ToUserName>", toUserName));
		sb.append(String.format("<FromUserName><![CDATA[%s]]></FromUserName>", fromUserName));
		sb.append(String.format("<CreateTime>%s</CreateTime>", String.valueOf(date.getTime())));
		sb.append("<MsgType><![CDATA[news]]></MsgType>");
		sb.append(String.format("<ArticleCount>%d</ArticleCount>", 1));
		sb.append("<Articles>");
		sb.append("<item>");
		sb.append(String.format("<Title><![CDATA[%s]]></Title>", "欢迎关注圆点"));
		sb.append(String.format("<Description><![CDATA[%s]]></Description>", "只需一步即可注册成为圆点会员"));
		sb.append(String.format("<PicUrl><![CDATA[%s]]></PicUrl>", "http://121.40.175.6/YuanDianZone/Welcome"));
		sb.append(String.format("<Url><![CDATA[%s]]></Url>", "http://www.baidu.com"));
		sb.append("</item>");
		sb.append("</Articles>");
		sb.append("</xml>");
		return sb.toString();
	}
}
