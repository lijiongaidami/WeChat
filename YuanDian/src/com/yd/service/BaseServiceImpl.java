package com.yd.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.yd.dao.IBaseDao;
import com.yd.exception.DaoException;

/**
 * 业务处理基接口实现
 */
/**
 *
 */
@Service
@Component(value = "baseService")
public class BaseServiceImpl implements IBaseService {
	@Autowired
	private IBaseDao baseDao;
	

	@Override
	public void add(Object entity) throws DaoException {
		try {
			this.baseDao.add(entity);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public Serializable save(Object entity) throws DaoException {
		try {
			return this.baseDao.save(entity);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void update(Object entity) throws DaoException {
		try {
			this.baseDao.update(entity);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void saveOrUpdate(Object entity) throws DaoException {
		try {
			this.baseDao.saveOrUpdate(entity);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void delete(Object entity) throws DaoException {
		try {
			this.baseDao.delete(entity);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void delete(Class<?> clazz, int id) throws DaoException {
		try {
			this.baseDao.delete(clazz, id);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void delete(Class<?> c, String ids) throws DaoException {
		try {
			this.baseDao.delete(c, ids);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void deleteEntities(Class<?> c, String ids) throws DaoException {
		try {
			this.baseDao.deleteEntities(c, ids);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void merge(Object entity) throws DaoException {
		try {
			this.baseDao.merge(entity);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public Object getObjectByID(Class<?> c, int id) throws DaoException {
		try {
			return this.baseDao.getObjectByID(c, id);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List<?> getAll(Class<?> c) throws DaoException {
		try {
			return this.baseDao.getAllObjects(c);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}


	@Override
	public void bulkUpdate(String sql) throws DaoException {
		try {
			this.baseDao.executeNativeSql(sql);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}



	@Override
	public List<?> getObjectList(String sql) throws DaoException {
		try {
			return this.baseDao.getObjectList(sql);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List<?> getListByNavtiveSql(String sql) throws DaoException {
		try {
			return this.baseDao.getObjectsByNativeSql(sql);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}


	@Override
	public void updateByHql(String hql) throws DaoException {
		try {
			this.baseDao.updateByHql(hql);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}






}
