package molab.main.java.util;

import java.util.Properties;

public class Wicloud {

	public static double parseDoubleValue(Object value) {
		return value == null ? 0 : (Double) value;
	}
	
	public static String getProperty(String key) {
		Properties p = PropertiesUtil.loadProperties(Path.cfg());
		if(p.containsKey(key)) {
			return p.getProperty(key);
		}
		return null;
	}
	
}
