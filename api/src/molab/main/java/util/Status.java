package molab.main.java.util;

public class Status {

	public static enum Common {
		ERROR_EXIST(-1), ERROR(0), SUCCESS(1);
		private int value;

		private Common(int value) {
			this.value = value;
		}

		public int getInt() {
			return value;
		}
	}
	
	public static enum Add {
		ERROR_CODE_EXIST(-2), ERROR_MON_EXIST(-1), ERROR(0), SUCCESS(1);
		private int value;

		private Add(int value) {
			this.value = value;
		}

		public int getInt() {
			return value;
		}
	}
	
	public static enum Signin {
		ERROR_PASSWORD(-2), ERROR_USERNAME(-1), ERROR(0), SUCCESS(1);
		private int value;

		private Signin(int value) {
			this.value = value;
		}

		public int getInt() {
			return value;
		}
	}

}