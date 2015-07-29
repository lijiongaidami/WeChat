package com.yd.util;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.fr.base.CodeUtils;
import com.fr.base.xml.XMLPrintWriter;
import com.fr.base.xml.XMLableReader;
import com.fr.data.impl.JDBCDatabaseConnection;
import com.fr.data.pool.DBCPConnectionPoolAttr;

public class MyJDBCDatabaseConnection  extends JDBCDatabaseConnection {

	private static final long serialVersionUID = -1622464907127518885L;

	@Override
    public void readXML(XMLableReader xmlablereader)
    {
        InputStream a = getClass().getClassLoader().getResourceAsStream("../../META-INF/context.xml");
        try {
			Document document = new SAXReader().read(a);
			Element node =  (Element)document.selectSingleNode("/Context/Resource");
			String driverClass = node.attribute("driverClass").getValue();
			String user = node.attribute("user").getValue();
			String password = node.attribute("password").getValue();
			String jdbcUrl = node.attribute("jdbcUrl").getValue();
			setDriver(driverClass);
			setURL(jdbcUrl); 
			setUser(user);
			setPassword(CodeUtils.passwordDecode(password));
        	setEncryptPassword(false);
        	DBCPConnectionPoolAttr dbcpconnectionpoolattr = new DBCPConnectionPoolAttr();
            setDbcpAttr(dbcpconnectionpoolattr);
            xmlablereader.readXMLObject(dbcpconnectionpoolattr);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@Override
    public void writeXML(XMLPrintWriter xmlprintwriter) {
    
	}
}
