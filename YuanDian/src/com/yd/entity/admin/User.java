package com.yd.entity.admin;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *用户实体表
 */
@Entity
@Table(name = "T_USER")
public class User implements Serializable   {

	private static final long serialVersionUID = 487092737452798527L;

	/** 用户主键 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PK_ID")
	private Integer id;

	@Column(name="OPEN_ID",unique=true,nullable = false)
	private String openID;
	
	/**
	 * 用户类型 用于标识 用户属于  1、普通用户组  2、商家管理组 3、配送员组
	 */
	@Column (name = "USER_TYPE")
	private Integer userType=1;
	
	/** 用户名 */
	@Column(name = "USER_NAME", length = 100)
	private String userName;
	
	/** 手机号 **/
	@Column(name = "MOBILE_Number",length = 20,unique=true)
	private String mobileNumber;

	/** 密码 */
	@Column(name = "PASSWORD", length = 150)
	private String password;
	
	/**
	 * 地址
	 */
	@Column(name = "ADDRESS" )
	private String address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
}
