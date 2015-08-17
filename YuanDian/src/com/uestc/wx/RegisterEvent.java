package com.uestc.wx;

import java.util.Date;

import com.uestc.util.Log;

/**
 * 处理用户的注册信息
 * @author justyoung
 *
 */
public class RegisterEvent {

	static Date date = new Date();

	/**
	 * 回复图文消息
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String responseTextEvent(String toUserName, String fromUserName) {
		String picURL = WXProperties.getValue("base_url") + WXProperties.getValue("subscribeImage");
		Log.logger.debug("注册图片的地址是：" + picURL);
		String registerURL = WXProperties.getValue("base_url") + WXProperties.getValue("register_url") + "?open_id=" + toUserName; // 加上客户的open_id
		Log.logger.debug("注册页面的链接地址是：" + registerURL);
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append(String.format("<ToUserName><![CDATA[%s]]></ToUserName>", toUserName));
		sb.append(String.format("<FromUserName><![CDATA[%s]]></FromUserName>", fromUserName));
		sb.append(String.format("<CreateTime>%s</CreateTime>", String.valueOf(date.getTime())));
		sb.append("<MsgType><![CDATA[news]]></MsgType>");
		sb.append(String.format("<ArticleCount>%d</ArticleCount>", 1));
		sb.append("<Articles>");
		sb.append("<item>");
		sb.append(String.format("<Title><![CDATA[%s]]></Title>", "注册圆点"));
		sb.append(String.format("<Description><![CDATA[%s]]></Description>", "只需一步即可注册成为圆点会员"));
		sb.append(String.format("<PicUrl><![CDATA[%s]]></PicUrl>", picURL));
		sb.append(String.format("<Url><![CDATA[%s]]></Url>", registerURL));
		sb.append("</item>");
		sb.append("</Articles>");
		sb.append("</xml>");
		return sb.toString();
	}
}
