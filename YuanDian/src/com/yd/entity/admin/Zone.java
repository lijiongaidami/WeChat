package com.yd.entity.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 地区——学校
 */
@Entity
@Table(name = "T_ZONE")
public class Zone implements Serializable{
	
	private static final long serialVersionUID = 9050330600011756635L;

	/** 用户主键 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PK_ID")
	private Integer id;
	
	/**
	 * 地区学校名称
	 */
	@Column(name = "name")
	private String name;
	
	@Column(name = "IS_DELETE")
	private Integer isDelete;

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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
