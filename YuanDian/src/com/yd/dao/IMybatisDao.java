package com.yd.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.yd.exception.DaoException;
import com.yd.vo.ListVo;

public interface IMybatisDao {

	public Object getObjectByID(String statement,int id) throws DaoException;

	public List<Object> getObjectList(String statement,Object parameter) throws DaoException;
	
	public ListVo<Object> getObjectPage(String statement, Map<String,Object> parameter ,RowBounds rowBounds) throws DaoException;

	public int update(String statement, Map<String, Object> parameter) throws DaoException;

	public int delete(String statement, Map<String, Object> parameter) throws DaoException;

	public int insert(String statement, Map<String, Object> parameter) throws DaoException;

	public Object getObjectByMap(String statement, Map<String, Object> paramMap) throws DaoException;

	public Object getUniqueObject(String statement, Object object) throws DaoException;

}
