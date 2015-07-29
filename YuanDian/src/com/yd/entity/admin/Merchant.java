package com.yd.entity.admin;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_MERCHANT")
public class Merchant implements Serializable {

	private static final long serialVersionUID = 9221603540908842347L;
	
	/** 用户主键 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PK_ID")
	private Integer id;
	
	/**
	 * 商家名称
	 */
	@Column (name = "NAME")
	private String name;
	
	/**
	 * 地址
	 */
	@Column (name = "ADDRESS")
	private String address; 
	
	/**
	 * 商家描述
	 */
	@Column (name = "DESCRIPTION")
	private String description;
	
	/**
	 * 商家图片地址
	 */
	@Column (name = "IMAGE_URL")
	private String imageURL;
	
	/**
	 * 商家后台管理登录名
	 */
	@Column (name = "LOG_NAME")
	private String logName;
	
	@Column (name = "PASSWORD")
	private String password;
	
	/**
	 * 商家所管理的微信用户的openID
	 */
	@Column (name = "OPEN_ID")
	private String openID;
	
	/**
	 * 商家的联系方式
	 */
	@Column (name = "CONTACT")
	private String contact;
	
	/**
	 * 是否删除标识位  0 未删除
	 * 				1 已删除
	 */
	@Column (name = "IS_DELETE")
	private int isDelete = 0;
	
	/**
	 * 地区ID
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn (name = "FK_ZONE_ID")
	private Zone ZoneId;
	
	
	
}
