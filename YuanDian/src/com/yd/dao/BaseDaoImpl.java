package com.yd.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.yd.exception.DaoException;

@Repository(value = "baseDao")
public class BaseDaoImpl extends HibernateDaoSupport implements IBaseDao {
	@Autowired
	public void setSessionFactoryOverride(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	/**
	 * 
	 * @TODO 执行本地sql,返回list
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<?> getObjectsByNativeSql(final String sql) throws DaoException {
		List<Object> list = this.getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws DaoException {
				List<Object> list = null;
				try {
					list = session.createSQLQuery(sql).list();
				} catch (Exception e) {
					e.printStackTrace();
					//throw new DaoException(e);
				}
				return list;
			}
		});
		return list;
	}

	/**
	 * 
	 * @TODO 执行本地sql语句，可以执行update，delete，insert等操作
	 * 
	 */
	public void executeNativeSql(final String sql) throws DaoException {
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws DaoException {
				try {
					session.createSQLQuery(sql).executeUpdate();
				} catch (Exception e) {
					throw new DaoException(e);
				}
				return null;
			}
		});
	}

	/**
	 * 
	 * @TODO 批量执行本地sql语句
	 * 
	 */
	public int[] batchExecuteNativeSql(final List<String> queryString) throws DaoException {
		int[] num = (int[]) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@SuppressWarnings("deprecation")
			public Object doInHibernate(Session session) throws DaoException {
				Connection connect;
				Statement statement;
				int[] ret = null;
				try {
					connect = session.connection();
					statement = connect.createStatement();
					for (int i = 0; i < queryString.size(); i++) {
						statement.addBatch(queryString.get(i));
					}
					connect.close();
					ret = statement.executeBatch();
				} catch (Exception e) {
					throw new DaoException(e);
				}
				return ret;
			}
		});
		return num;
	}

	/**
	 * 
	 * @TODO 保存一个实体
	 * 
	 */
	public Serializable save(Object entity) throws DaoException {
		Serializable serializable = null;
		try {
			serializable = this.getHibernateTemplate().save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
		return serializable;
	}

	@Override
	public void add(Object entity) throws DaoException {
		try {
			this.getHibernateTemplate().persist(entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void merge(Object entity) throws DaoException {
		try {
			this.getHibernateTemplate().merge(entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	/**
	 * 
	 * @TODO 删除实体
	 * 
	 */
	@Override
	public Object delete(Object entity) throws DaoException {
		try {
			this.getHibernateTemplate().delete(entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return entity;
	}

	@Override
	public void delete(Class<?> clazz, int id) throws DaoException {
		try {
			String hql = "update " + clazz.getName() + " a set a.isDelete = 1 where a.id = " + id;
			this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(Class<?> clazz, String ids) throws DaoException {
		try {
			String hql = "update " + clazz.getName() + " a SET a.isDelete = 1 where a.id in (" + ids + ")";
			this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void deleteEntities(Class<?> clazz, String ids) throws DaoException {
		try {
			String hql = "delete from " + clazz.getName() + " a where a.id in (" + ids + ")";
			this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	/**
	 * 
	 * @TODO 获取所有实体
	 * 
	 */
	public List<?> getAllObjects(Class<?> c) throws DaoException {
		List<?> list = new ArrayList<Object>();
		try {
			list = this.getHibernateTemplate().find("select o from " + c.getName() + " o order by o.id desc");
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return list;
	}

	@Override
	public List<?> getObjectListByMap(String hql, Map<?, ?> map) throws DaoException {
		List<?> result = new ArrayList<Object>();
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		if (map != null) {
			Iterator<?> it = map.keySet().iterator();
			while (it.hasNext()) {
				Object key = it.next();
				query.setParameter(key.toString(), map.get(key));
			}
		}
		result = query.list();
		return result;
	}

	@Override
	public List<?> getObjectListByList(String hql, List<?> list) throws DaoException {
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				query.setParameter(i, list.get(i));
			}
		}
		return query.list();
	}

	@Override
	public List<?> getObjectList(String hql) throws DaoException {
		try {
			Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
			return query.list();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	/**
	 * 
	 * @TODO 通过id获取实体
	 * 
	 */
	public Object getObjectByID(Class<?> cls, int id) throws DaoException {
		Object obj = null;
		try {
			obj = this.getHibernateTemplate().get(cls, id);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return obj;
	}

	/**
	 * 
	 * @TODO update 实体
	 * 
	 */
	public void update(Object entity) throws DaoException {
		try {
			this.getHibernateTemplate().update(entity);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void updateByHql(String hql) throws DaoException {
		try {
			this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}


	/**
	 * 
	 * @TODO 添加或者保存实体
	 * 
	 */
	public void saveOrUpdate(Object entity) throws DaoException {
		try {
			this.getHibernateTemplate().saveOrUpdate(entity);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void bulkUpdate(final String sql) throws DaoException {
		try {
			this.getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					session.createSQLQuery(sql).executeUpdate();
					return null;
				}
			});
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<?> getListByNavtiveSql(final String sql) throws DaoException {
		try {
			List<?> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					List<?> list = session.createSQLQuery(sql).list();
					return list;
				}
			});
			return list;
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}


	/**
	 * 通过命名参数查找数据
	 */
	public List<?> getListByParams(String hql, Map<?, ?> map) throws DaoException {
		List<?> result = null;
		try {
			Query query = this.getSession().createQuery(hql);

			Iterator<?> it = map.keySet().iterator();
			while (it.hasNext()) {
				Object key = it.next();
				query.setParameter(key.toString(), map.get(key));
			}

			result = query.list();
		} catch (RuntimeException e) {
			throw new DaoException(e);
		}
		return result;
	}
	
	/**
	 * 
	 * 根据条件查找数据(准确查找)
	 * 
	 */
	public List<?> getListByExactCondition(Class<?> cls, Map<String, Object> condition) throws DaoException {
		StringBuffer sb = new StringBuffer();
		sb.append("select obj from " + cls.getName() + " obj where 1=1 ");
		List<?> list = new ArrayList<Object>();
		if (null != condition) {
			Iterator<?> it = condition.keySet().iterator();
			Object value;
			String key;
			while (it.hasNext()) {
				key = (String) it.next();
				value = condition.get(key);
				if ("".equals(value) || value == null) {
					continue;
				} else if (value instanceof String) {
					sb.append("and obj." + key + " = '" + value + "' ");
				} else if (value instanceof Integer || value instanceof Long || value instanceof Double || value instanceof Float) {
					String str = String.valueOf(value);
					if (!str.equals("0")) {
						sb.append("and obj." + key + "=" + value + " ");
					}
				}
			}
		}
		try {
			list = this.getHibernateTemplate().find(sb.toString());
			return list;
		} catch (DaoException e) {
			throw new DaoException(e);
		}
	}
	
	
	/**
	 * 
	 * 获取sql语句查询的所有记录的条数
	 * 
	 */
	public int getCount(final String sql, final boolean isHql) throws DaoException {
		Query query = isHql ? this.getSession().createQuery(sql) : this.getSession().createSQLQuery(sql);
		return query.list().size();
	}
}
