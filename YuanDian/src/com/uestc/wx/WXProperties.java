package com.uestc.wx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.uestc.util.Log;

public class WXProperties {
	private static Properties properties;

	static {
		init();
	}

	public static void init() {
		properties = new Properties();
		String pathString = WXProperties.class.getResource("/").getPath();
		pathString = pathString.substring(0, pathString.lastIndexOf("classes")) + "conf/WX.properties";
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(pathString);
			properties.load(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.logException(e);
			properties = null;
		} catch (IOException e) {
			e.printStackTrace();
			Log.logException(e);
			properties = null;
		} finally {
			try {
				if (fs != null)
					fs.close();
			} catch (IOException e) {
				e.printStackTrace();
				Log.logException(e);
			}
		}
	}

	public static String getValue(String key) {
		if (properties == null) {
			return null;
		}
		return properties.getProperty(key);
	}

}
