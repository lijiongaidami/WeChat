package com.yd.service;

import java.io.Serializable;
import java.util.List;

import com.yd.exception.DaoException;

/**
 * 业务处理基接口
 */
public interface IBaseService {

	
	/**
	 * 保存信息到数据库
	 * 
	 */
	public void add(Object entity) throws DaoException;

	/**
	 * 保存信息，并返回主键(实体是游离态不会出错)
	 * 
	 */
	public Serializable save(Object entity) throws DaoException;

	/**
	 * 更新信息
	 * 
	 */
	public void update(Object entity) throws DaoException;

	/**
	 * 更新或保存信息
	 * 
	 */
	public void saveOrUpdate(Object entity) throws DaoException;

	/**
	 * 从数据库真正删除数据
	 * 
	 */
	public void delete(Object entity) throws DaoException;

	/**
	 * 根据id，逻辑删除数据
	 * 
	 */
	public void delete(Class<?> clazz, int id) throws DaoException;

	/**
	 * 逻辑删除表中记录(前提是:传人的实体类必须要有id字段作为主键)
	 * 
	 */
	void delete(Class<?> c, final String ids) throws DaoException;

	/**
	 * 物理删除表中的数据
	 * 
	 */
	void deleteEntities(Class<?> c, String ids) throws DaoException;

	/**
	 * 追加信息到已有的实体中
	 * 
	 */
	public void merge(Object entity) throws DaoException;

	/**
	 * 通过实体和ID查询信息
	 * 
	 */
	public Object getObjectByID(Class<?> c, int id) throws DaoException;

	/**
	 * 查询该实体下的全部信息
	 * 
	 */
	public List<?> getAll(Class<?> c) throws DaoException;


	/**
	 * 执行SQL的删除，更新，添加等 native SQL
	 * 
	 */
	public void bulkUpdate(String sql) throws DaoException;


	/**
	 * 根据HSQL，取得结果列表
	 * 
	 */
	public List<?> getObjectList(String sql) throws DaoException;

	/**
	 * 通过native sql 取得记录列表
	 * 
	 */
	public List<?> getListByNavtiveSql(String sql) throws DaoException;


	/**
	 * 根据hql进行更新操作
	 * 
	 */
	public void updateByHql(String hql) throws DaoException;

}
