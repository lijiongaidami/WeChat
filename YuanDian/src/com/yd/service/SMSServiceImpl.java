package com.yd.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fr.base.core.json.JSONObject;
import com.yd.entity.admin.ValidateCode;
import com.yd.util.DateUtil;
import com.yd.util.SMSUtil;

@Service
@Component(value="smsService")
public class SMSServiceImpl extends BaseServiceImpl implements ISMSService{
	
	@Override
	@SuppressWarnings({ "rawtypes" })
	public String sendSMS(String phoneNum) throws Exception{
		String randcode = SMSUtil.createRandCode();
		String result = SMSUtil.send(phoneNum,randcode);
		System.out.println(result);
		JSONObject json = new JSONObject(result);
		result = json.get("res_code").toString();
		if("0".equals(result)){
			ValidateCode validateCode = new ValidateCode();
			validateCode.setPhoneNumber(phoneNum);
			validateCode.setValidateCode(randcode);
			List list = this.getListByNavtiveSql("select PK_ID from t_validate_code t where t.PHONE_NUMBER = '"+phoneNum+"'");
			if(list.size()==0)
				this.save(validateCode);
			else{
				validateCode.setId(NumberUtils.toInt(list.get(0).toString()));
				this.update(validateCode);
			}
			return "success";
		}
		else
			return json.get("res_message").toString();
	}
	@Override
	@SuppressWarnings("rawtypes")
	public String validateCode(String phoneNumber,String validateCode){
		List list = this.getListByNavtiveSql("select create_time from t_validate_code t where t.PHONE_NUMBER = '"+phoneNumber+"' And t.VALIDATE_CODE='"+validateCode+"'");
		if(list.size()==0)
			return "validate code error!";
		Date date = DateUtil.stringToDate(list.get(0).toString(), "yyyy-MM-dd HH:mm:ss");
		Date nowDate = DateUtil.nowDate("yyyy-MM-dd HH:mm:ss");
		long temp = (nowDate.getTime()-date.getTime())%(1000*3600)/1000/60;
		if(temp>30)
			return "over time";
		if(temp<0)
			return "time error";
		return "success";
	}
}
