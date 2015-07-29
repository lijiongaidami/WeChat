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
 * 商家商品 关系表
 */
@Entity
@Table(name = "T_MERCHANT_ITEM")
public class Merchant_Item implements Serializable{
	
	private static final long serialVersionUID = 1171500299228904925L;

	/** 用户主键 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PK_ID")
	private Integer id;
	
	/**
	 * 名称
	 */
	@Column(name = "NAME")
	private String name;
	
	/**
	 * 价格
	 */
	@Column(name = "PRICE")
	private double price;
	
	/**
	 * 图片地址
	 */
	@Column(name = "IMAGE_URL")
	private double imageUrl;
	
	/**
	 * 商家id
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn (name = "FK_MERCHANT_ID")
	private Merchant merchantId;
	
	/**
	 * 商品id
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn (name = "FK_ITEM_ID")
	private Item itemID;
	
	/**
	 * 详细描述
	 */
	@Column(name = "DESCRIPTION")
	private String description;
	
	/**
	 * 销量
	 */
	@Column(name = "SALES_NUM")
	private Integer salesNum; 
	
	/**
	 * 库存
	 */
	@Column(name = "STOCK")
	private Integer stock;
	
	/**
	 * 计量单位
	 */
	@Column(name = "UNIT")
	private String unit;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(double imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Merchant getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Merchant merchantId) {
		this.merchantId = merchantId;
	}

	public Item getItemID() {
		return itemID;
	}

	public void setItemID(Item itemID) {
		this.itemID = itemID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
