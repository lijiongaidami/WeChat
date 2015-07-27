/**
 * 
 * 成都铁路局科研所
 * <ul>
 * <li>Author: 焦运磊</li>
 * <li>E-Mail: jylapple@163.com</li>
 * <li>T-Phone: 18628168078</li>
 * <li>Date: 2012-12-11</li>
 * <li>Description:</li>
 * <li>+-History-------------------------------------+</li>
 * <li>| Date Author Description</li>
 * <li>|2012-12-11 焦运磊 Created</li>
 * <li>+------------------------------------------------</li>
 * </ul>
 */
package com.yd.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.yd.exception.DaoException;
import com.yd.vo.ListVo;

/**
 * mybatis数据库实现基类
 */
@Repository
@Component(value="mybatisDao")
public class MybatisDaoImpl implements IMybatisDao {

	@Autowired
	private SqlSessionTemplate sqlSession;  
	
	@Override
	public Object getObjectByID(String statement, int id) {
		Object object;
		try {
			object = sqlSession.selectOne(statement, id);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return object;
	}
	
	@Override
	public Object getObjectByMap(String statement, Map<String,Object> paramMap) {
		Object object;
		try {
			object = sqlSession.selectOne(statement, paramMap);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return object;
	}
	
	@Override
	public Object getUniqueObject(String statement, Object object) {
		Object obj;
		try {
			obj = sqlSession.selectOne(statement, object);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return obj;
	}


	@Override
	public List<Object> getObjectList(String statement, Object param) {
		List<Object> objectList = new ArrayList<Object>();
		try {
			objectList = sqlSession.selectList(statement, param);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return objectList;
	}

	@Override
	public ListVo<Object> getObjectPage(String statement, Map<String,Object> parameter, RowBounds rowBounds) {
		ListVo<Object> listVo = new ListVo<Object>();
		try {
			List<Object> listObect = sqlSession.selectList(statement, parameter, rowBounds);
			listVo.setList(listObect);
			listVo.setTotalSize((Integer)parameter.get("pageCount"));
		} catch (Exception e) {
			throw new DaoException(e);
		} 
		return listVo;
	}
	
	@Override
	public int insert(String statement, Map<String,Object> parameter) throws DaoException {
		try {
			return sqlSession.insert(statement, parameter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} 
	}
	
	
	@Override
	public int update(String statement, Map<String,Object> parameter) throws DaoException {
		try {
			return sqlSession.update(statement, parameter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} 
	}
	
	@Override
	public int delete(String statement, Map<String,Object> parameter) throws DaoException {
		try {
			return sqlSession.delete(statement, parameter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} 
	}
	
}
