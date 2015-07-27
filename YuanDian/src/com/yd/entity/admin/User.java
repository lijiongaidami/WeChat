package com.yd.entity.admin;


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
public class User   {

	private static final long serialVersionUID = 487092737452798527L;

	/** 用户主键 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PK_ID")
	private Integer id;

	/** 用户名 */
	@Column(name = "USER_NAME", length = 100, nullable = false)
	private String userName;

	/** 登录名 */
	@Column(name = "LOGIN_NAME", length = 50)
	private String loginName;

	/** 密码 */
	@Column(name = "PASSWORD", length = 150)
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
