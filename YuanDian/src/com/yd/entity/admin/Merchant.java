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

/**
 * 商家实体表
 */
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
	private Integer isDelete = 0;
	
	/**
	 * 地区ID
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn (name = "FK_ZONE_ID")
	private Zone ZoneId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Zone getZoneId() {
		return ZoneId;
	}

	public void setZoneId(Zone zoneId) {
		ZoneId = zoneId;
	}
	
}
