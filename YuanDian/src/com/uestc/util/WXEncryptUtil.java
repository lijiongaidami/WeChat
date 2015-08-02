package com.uestc.util;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

public class WXEncryptUtil {

	static WXBizMsgCrypt pc;

	static {
		try {
			pc = new WXBizMsgCrypt(WeiXinConfig.TOKEN, WeiXinConfig.ENCODING_AES_KEY, WeiXinConfig.APP_ID);
		} catch (AesException e) {
			pc = null;
			Log.logException(e);
		}
	}

	public static String decodeXML(String msgSignature, String postData, String timeStamp, String nonce)
			throws AesException {
		if (pc == null)
			pc = new WXBizMsgCrypt(WeiXinConfig.TOKEN, WeiXinConfig.ENCODING_AES_KEY, WeiXinConfig.APP_ID);
		return pc.decryptMsg(msgSignature, timeStamp, nonce, postData);
	}
	
	public static String encodeXML(String replyMsg, String timeStamp, String nonce) throws AesException{
		if (pc == null)
			pc = new WXBizMsgCrypt(WeiXinConfig.TOKEN, WeiXinConfig.ENCODING_AES_KEY, WeiXinConfig.APP_ID);
		return pc.encryptMsg(replyMsg, timeStamp, nonce);
	}

}
