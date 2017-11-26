package molab.main.java.util;

import java.io.File;

public class Path {
	
	public static String cfg() {
		return getWebInf() + "/cfg.properties";
	}
	
	private static String getWebInf() {
		return new File(Path.class.getResource("/").getFile()).getParent();
	}

}
