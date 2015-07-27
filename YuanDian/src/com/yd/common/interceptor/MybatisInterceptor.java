package com.yd.common.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.log4j.Logger;

/**
 * 用户登录拦截器
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class MybatisInterceptor implements Interceptor {

	protected final Logger log = Logger.getLogger(this.getClass());

	
	@SuppressWarnings("unchecked")
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		Connection connection = (Connection) invocation.getArgs()[0];
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, new DefaultObjectFactory(), new DefaultObjectWrapperFactory());
		String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		log.info(originalSql);
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
			return invocation.proceed();
		}
		Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
		String dialect = null;
		try {
			dialect = configuration.getVariables().getProperty("dialect").toUpperCase();
			if (dialect == null || "".equals(dialect)) {
				throw new RuntimeException("the value of the dialect property in configuration.xml is not defined");
			}
		} catch (Exception e) {
			System.out.println("mybatis-config.xml中未设置数据库类型");
		}

		ParameterHandler parameter = statementHandler.getParameterHandler(); 
		if ("MYSQL".equals(dialect)) {
			if(parameter !=null && parameter.getParameterObject() instanceof Map) {
				int count = this.getCount(configuration, connection, statementHandler,mappedStatement);
				((Map<String, Object>) parameter.getParameterObject()).put("pageCount", count); //为参数map赋值，用以保存有多少页
			}
			metaStatementHandler.setValue("delegate.boundSql.sql", this.getMysqlLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit()));
		}
		metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);

		metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
		// System.out.println(" 生成分页SQL : " + boundSql.getSql());
		return invocation.proceed();
	}

	private String getMysqlLimitString(String sql, int offset, int limit) {
		StringBuffer mysql = new StringBuffer(sql.trim());
		mysql.append(" limit");
		mysql.append(" " + offset);
		mysql.append("," + limit);
		return mysql.toString();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties arg0) {

	}
	
	private int getCount(Configuration configuration,Connection connection,StatementHandler statementHandler,MappedStatement mappedStatement) {
		int count = 0;
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		try {
			BoundSql boundSql = statementHandler.getBoundSql();
			Object parameterObject = statementHandler.getParameterHandler().getParameterObject();
			String countSql = "select count(0) from (" + boundSql.getSql() + ")" + " as temp_table"; //记录统计    
			List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();
			
			countStmt = connection.prepareStatement(countSql);    
			BoundSql newBoundSql = new BoundSql(configuration, countSql, parameterMappingList, parameterObject);
			setParameters(countStmt,mappedStatement,newBoundSql,parameterObject);    
			rs = countStmt.executeQuery();    
			if (rs.next()) {    
			    count = rs.getInt(1);    
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
    /**  
     * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler  
     * @param ps  
     * @param mappedStatement  
     * @param boundSql  
     * @param parameterObject  
     * @throws SQLException  
     */    
    private void setParameters(PreparedStatement ps,MappedStatement mappedStatement,BoundSql boundSql,Object parameterObject) throws SQLException {    
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());    
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();    
        if (parameterMappings != null) {    
            Configuration configuration = mappedStatement.getConfiguration();    
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();    
            MetaObject metaObject = parameterObject == null ? null: configuration.newMetaObject(parameterObject);    
            for (int i = 0; i < parameterMappings.size(); i++) {    
                ParameterMapping parameterMapping = parameterMappings.get(i);    
                if (parameterMapping.getMode() != ParameterMode.OUT) {    
                    Object value;    
                    String propertyName = parameterMapping.getProperty();    
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);    
                    if (parameterObject == null) {    
                        value = null;    
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {    
                        value = parameterObject;    
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {    
                        value = boundSql.getAdditionalParameter(propertyName);    
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)&& boundSql.hasAdditionalParameter(prop.getName())) {    
                        value = boundSql.getAdditionalParameter(prop.getName());    
                        if (value != null) {    
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));    
                        }    
                    } else {    
                        value = metaObject == null ? null : metaObject.getValue(propertyName);    
                    }    
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();    
                    if (typeHandler == null) {    
                        throw new ExecutorException("There was no TypeHandler found for parameter "+ propertyName + " of statement "+ mappedStatement.getId());    
                    }    
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());    
                }    
            }    
        }    
    }  
}
