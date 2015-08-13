package com.yd.service;




public interface ISMSService extends IBaseService{

	public String validateCode(String phoneNumber,String validateCode);
	
	public String sendSMS(String phoneNum) throws Exception;
	
}
