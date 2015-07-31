package com.yd.service;

import java.util.Map;

import com.yd.exception.ServiceException;





public interface IUserService extends IBaseService{

	/**
	 * 用于检验用户OpenID 是否注册手机号
	 * 
	 * 
	 *@author 王海涛
	 *@date 2015年7月29日
	 *@param openID 
	 *@return
	 *@return boolean 未注册返回false  已注册返回true
	 */
	public int checkIsRegist(String openID)  throws ServiceException;
	
	/**
	 * 用户注册
	 * 
	 * 
	 *@author 王海涛
	 *@date 2015年7月29日
	 *@param paramMap 
	 *@return
	 *@return int
	 */
	public int registAction(Map<String,Object> paramMap)  throws ServiceException;
}
