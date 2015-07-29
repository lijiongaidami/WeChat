package com.yd.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yd.exception.DaoException;


public interface IBaseDao {
	/**
	 *
	 * @TODO	执行本地sql,返回list
	 *
	 */
	public List<?> getObjectsByNativeSql(final String sql) throws DaoException;
	
	/**
	 *
	 * @TODO	执行本地sql语句，可以执行update，delete，insert等操作
	 *
	 */
	public void executeNativeSql(final String sql) throws DaoException;
	
	/**
	 *
	 * @TODO	批量执行本地sql语句
	 *
	 */
	public int[] batchExecuteNativeSql(final List<String> queryString) throws DaoException;
	/**
	 *
	 * @TODO	保存一个实体
	 *
	 */
	public Serializable save(Object entity) throws DaoException;
	
	/**
	 * 添加一个实体
	 */
	public void add(Object entity) throws DaoException;
	
	/**
	 * 保存一个实体
	 */
	public void merge(Object entity) throws DaoException;
	
	/**
	 *
	 * @TODO	update 实体
	 *
	 */
	public void update(Object entity) throws DaoException;
	
	/**
	 * 根据hql来执行更新操作
	 */
	public void updateByHql(String hql) throws DaoException;
	
	/**
	 *
	 * @TODO	添加或者保存实体
	 *
	 */
	public void saveOrUpdate(Object entity) throws DaoException;
	
	/**
	 *
	 * @TODO	删除实体
	 *
	 */
	public Object delete(Object entity) throws DaoException;
	
	/**
	 * 根据id，删除数据
	 */
	public void delete(Class<?> clazz,int id) throws DaoException;
	
	/**
	 * 根据ids,字符串，删除数据
	 */
	public void delete(Class<?> c, final String ids) throws DaoException;
	
	/**
	 * 物理删除ids指向的对象
	 */
	public void deleteEntities(Class<?> clazz, String ids) throws DaoException;

	/**
	 *
	 * @TODO	获取所有实体
	 *
	 */
	public List<?> getAllObjects(Class<?> c) throws DaoException;
	
	/**
	 * 通地hql语句返回查询list
	 */
	public List<?> getObjectListByMap(String hql,Map<?,?> map) throws DaoException;
	
	/**
	 * 通地hql语句返回查询list
	 */
	public List<?> getObjectListByList(String hql,List<?> list) throws DaoException;
	
	/**
	 * 通地hql语句返回查询list
	 */
	public List<?> getObjectList(String hql) throws DaoException;
	
	/**
	 *
	 * @TODO	通过id获取实体
	 *
	 */
	public Object getObjectByID(Class<?> cls, int id) throws DaoException;

	
	/**
	 * 执行SQL的删除，更新，添加等 native SQL
	 * 
	 */
	public void bulkUpdate(String sql) throws DaoException;
	
	
	/**
	 * 通过native sql 取得记录列表
	 * 
	 */
	public List<?> getListByNavtiveSql(String sql) throws DaoException;
	
}
