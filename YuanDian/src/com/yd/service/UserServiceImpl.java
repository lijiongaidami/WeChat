package com.yd.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.yd.entity.admin.User;
import com.yd.exception.ServiceException;

public class UserServiceImpl extends BaseServiceImpl implements IUserService{

	@Override
	public int checkIsRegist(String openID) throws ServiceException{
		try {
			String sql = "select * from t_user t where t.OPEN_ID='"+openID+"' and t.MOBILE_Number IS NOT NULL AND t.MOBILE_Number !='' ";
			List<?> list=  this.getBaseDao().getListByNavtiveSql(sql);
			if(list.size()==0)
				return 0;
			else
				return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException(e); 
		}
	}

	@Override
	public int registAction(Map<String, Object> paramMap) throws ServiceException{
		try {
			//获取用户注册数据
			String openID = (String) paramMap.get("openID");
			String mobileNumber = (String) paramMap.get("mobileNumber");
			String userName = (String) paramMap.get("userName");
			String type = (String)	paramMap.get("type");
			
			//创建用户对象
			User user = new User();
			if(!StringUtils.isBlank(openID))
				user.setOpenID(openID);
			if(!StringUtils.isBlank(mobileNumber))
				user.setMobileNumber(mobileNumber);
			
			return 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
}
