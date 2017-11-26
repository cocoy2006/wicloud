package molab.main.java.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MathUtil {

	private static final int SCALE = 2;

	public static double divide(double x, double y) {
		return divide(x, y, SCALE);
	}
	
	public static double divide(double x, double y, int scale) {
		if(y == 0) {
			return 0;
		}
		BigDecimal bdx = new BigDecimal(Double.toString(x));
		BigDecimal bdy = new BigDecimal(Double.toString(y));
		return bdx.divide(bdy, scale, BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}
	
	public static double rescale(double d) {
		return rescale(d, SCALE);
	}

	public static double rescale(double d, int scale) {
		String pattern = "0.";
		for (int i = 0; i < scale; i++) {
			pattern += "#";
		}
		DecimalFormat format = new DecimalFormat(pattern);
		return Double.valueOf(format.format(d));
	}
	
	public static float rescale(float f) {
		return rescale(f, SCALE);
	}

	public static float rescale(float f, int scale) {
		String pattern = "0.";
		for (int i = 0; i < scale; i++) {
			pattern += "#";
		}
		DecimalFormat format = new DecimalFormat(pattern);
		return Float.valueOf(format.format(f));
	}

}
