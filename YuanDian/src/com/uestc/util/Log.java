package com.uestc.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {
	public static Logger logger = Logger.getLogger(Log.class);

	static {
		Init();
	}

	private static void Init() {
		String pathString = Log.class.getResource("/").getPath();
		pathString = pathString.substring(0, pathString.lastIndexOf("classes")) + "conf/log4j.properties";
		PropertyConfigurator.configure(pathString);
	}

	public static void logException(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw, true));
		logger.error(sw.toString());
	}
}
