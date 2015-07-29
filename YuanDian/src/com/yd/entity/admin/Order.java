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
 * 订单表
 */
@Entity
@Table(name = "T_ORDER")
public class Order implements Serializable{
	
	private static final long serialVersionUID = -8720483661806706146L;

	/** 用户主键 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PK_ID")
	private Integer id;
	
	/**
	 * 商家商品关系表的ID
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn (name = "FK_MERCHANT_ITEM_ID")
	private Merchant_Item merchantItemID;
	
	/**
	 * 订单号
	 */
	@Column (name = "ORDER_NUM")
	private String orderNum;
	
	/**
	 * 订单状态：0未送货，1送货中，2已确认，3取消订单
	 */
	@Column (name = "STATUS")
	private Integer status;
	
	/**
	 * 是否支付：0未支付，1支付
	 */
	@Column (name ="isPaid")
	private Integer isPaid;
	
	/**
	 * 总价
	 */
	@Column (name = "TOLAL_PRICE")
	private Double totalPrice;
	
	/**
	 * 单价
	 */
	@Column (name = "UNIT_PRICE")
	private Double unitPrice;
	
	/**
	 * 删除状态, 0未删除，1删除
	 */
	@Column (name = "IS_DELETE")
	private Integer isDeleted;

	/**
	 * 订单创建时间
	 */
	@Column (name = "CREATE_TIME")
	private String createTime;
	
	/**
	 * 订单完成时间
	 */
	@Column (name = "FINEISHED_TIME")
	private String fin1ishedTime;
	
	/**
	 * 下单用户的ID
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn (name = "FK_USER_ID")
	private User user;
	
	/**
	 * 订单的地址
	 */
	@Column(name= "ADDRESS")
	private String address;
	
	/**
	 * 联系电话
	 */
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Merchant_Item getMerchantItemID() {
		return merchantItemID;
	}

	public void setMerchantItemID(Merchant_Item merchantItemID) {
		this.merchantItemID = merchantItemID;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Integer isPaid) {
		this.isPaid = isPaid;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFin1ishedTime() {
		return fin1ishedTime;
	}

	public void setFin1ishedTime(String fin1ishedTime) {
		this.fin1ishedTime = fin1ishedTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
