package com.yd.action;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.xml.sax.SAXException;

import com.qq.weixin.mp.aes.AesException;
import com.uestc.util.EncryptUtil;
import com.uestc.util.Log;
import com.uestc.util.WXEncryptUtil;
import com.uestc.util.WeiXinConfig;
import com.uestc.wx.HandleMessage;

/**
 * 
 * @author justyoung
 *
 */
@Namespace(value = "/")
@ParentPackage("default")
public class CheckServer extends BaseAction {


	@Action (value = "CheckServer")
	public void receiveMsgFromWX() throws IOException {
		HttpServletRequest request = this.getHttpServletRequest();
		HttpServletResponse response = this.getHttpServletResponse();
		if (request.getMethod().equals("GET")) {
			firstCheck(request, response);
			return;
		}
		if (request.getMethod().equals("POST")) {
			Log.logger.info("收到服务器的POST请求:" + request.getRemoteAddr());
			PrintWriter pw = response.getWriter();

			// 验证是否来自微信服务器
			String timeStamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			String signature = request.getParameter("signature");
			if (nonce == null || timeStamp == null || signature == null) {
				Log.logger.debug("没有收到timestamp、nonce和signature参数");
				return;
			}
			if (!checkSignature(signature, timeStamp, nonce, WeiXinConfig.TOKEN)) {
				Log.logger.info("不是来自微信服务器的请求 " + request.getRemoteAddr());
			}

			String encryptType = request.getParameter("encrypt_type");
			String msgSignature = null;
			// 提取必要的参数
			if (encryptType != null && encryptType.equals("aes")) {
				msgSignature = request.getParameter("msg_signature");
				if (msgSignature == null) {
					Log.logger.info("msg_signature参数");
					pw.write("");
					return;
				}
			}

			// 接收http报文的body部分，即XML字符串
			BufferedInputStream bis = new BufferedInputStream(request.getInputStream());
			StringBuffer stringBuffer = new StringBuffer();
			byte[] buffer = new byte[4096];
			int len = 0;
			while ((len = bis.read(buffer)) != -1)

			{
				stringBuffer.append(new String(buffer, 0, len));
			}

			String fromPost = stringBuffer.toString();
			if (fromPost == null || fromPost.equals(""))

			{
				Log.logger.info("收到空请求");
				pw.write("");
				return;
			}

			// 解密报文
			if (encryptType != null && encryptType.equals("aes"))

			{
				try {
					fromPost = WXEncryptUtil.decodeXML(msgSignature, fromPost, timeStamp, nonce);
					Log.logger.debug(fromPost);
				} catch (AesException e) {
					Log.logException(e);
					pw.write("");
					return;
				}
			}

			// 处理用户的消息或事件
			String responseData = null;
			try {
				try {
					responseData = HandleMessage.handle(fromPost);
				} catch (IOException e) {
					Log.logException(e);
					return;
				}
				Log.logger.debug(responseData);
			} catch (ParserConfigurationException | SAXException e) {
				Log.logException(e);
				pw.write("");
				return;
			}

			// 加密报文
			if (encryptType != null && encryptType.equals("aes")) {
				if (responseData == null || responseData.equals("")) {
					Log.logger.info("用户的回复报文生成错误");
					pw.write("");
					return;
				}
				try {
					responseData = WXEncryptUtil.encodeXML(responseData, timeStamp, nonce);
				} catch (AesException e) {
					Log.logException(e);
					pw.write("");
					return;
				}
			}

			try {
				pw.write(responseData);
				Log.logger.debug(responseData);
				pw.flush();
				pw.close();
			} catch (Exception e) {
				Log.logException(e);
				return;
			}
		}
	}
	
	/**
	 * 用于服务器Get请求的验证
	 * 
	 * @param request
	 * @param response
	 */
	private void firstCheck(HttpServletRequest request, HttpServletResponse response) {
		Log.logger.info("收到来自微信服务器的验证请求： " + request.getRemoteAddr() + " " + request.getRemoteHost());
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String token = WeiXinConfig.TOKEN;
		if (token == null || timestamp == null || nonce == null || signature == null || echostr == null)
			return;

		if (!checkSignature(signature, timestamp, nonce, token))
			return;

		try {
			PrintWriter pw = response.getWriter();
			pw.write(echostr);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			Log.logException(e);
			return;
		}
	}

	private boolean checkSignature(String signature, String timestamp, String nonce, String token) {
		String[] array = new String[] { token, timestamp, nonce };
		Arrays.sort(array);
		StringBuilder content = new StringBuilder();
		for (String i : array) {
			content.append(i);
		}
		String tempStr = null;
		tempStr = EncryptUtil.SHA1(content.toString());
		return tempStr.equals(signature);
	}
	
}
