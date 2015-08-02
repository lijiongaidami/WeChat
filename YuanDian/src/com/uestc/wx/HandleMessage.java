package com.uestc.wx;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.uestc.util.Log;
import com.uestc.util.WeiXinConfig;

public class HandleMessage {
	
	/**
	 * 处理解密后的微信信息。
	 * @param postData
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static String handle(String postData) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(postData);
		InputSource is = new InputSource(sr);
		Document document = db.parse(is);

		Element root = document.getDocumentElement();
		NodeList fromUserNameList = root.getElementsByTagName("FromUserName");
		NodeList toUserNameList = root.getElementsByTagName("ToUserName");
		NodeList MsgTypeList = root.getElementsByTagName("MsgType");

		String fromUserName = fromUserNameList.item(0).getTextContent();
		String toUserName = toUserNameList.item(0).getTextContent();
		String msgType = MsgTypeList.item(0).getTextContent();
		
		// 处理Event
		if (msgType.equals(WeiXinConfig.EVENT)){
			String event = root.getElementsByTagName("Event").item(0).getTextContent();
			if (event.equals(WeiXinConfig.EVENT_SUBSCRIBE)) {
				Log.logger.info(String.format("收到用户：%s的订阅事件", fromUserName));
				return SubscribeEvent.responseSubscribeEvent(fromUserName, toUserName);  // 发送方和接收方这里要做调换！
			}
		}
		
		return null;
	}

}
