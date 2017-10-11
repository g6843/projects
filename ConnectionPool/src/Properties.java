public class Properties {
	public static final int IDLE_TIMEOUT = 60000;
	private static String DRIVER_NAME = "com.edb.Driver";
	private static String URL = "jdbc:edb://localhost:5444/xyz";
	private static String USERNAME = "tkcsowner";
	private static String PASSWORD = "tkcsowner";
	private static int MIN_POOL_SIZE = 10;
	private static int MAX_POOL_SIZE = 10;
	private static int MAX_STATEMENTS = 100;

	public static String getDRIVER_NAME() {
		return DRIVER_NAME;
	}

	public static String getURL() {
		return URL;
	}

	public static String getUSERNAME() {
		return USERNAME;
	}

	public static String getPASSWORD() {
		return PASSWORD;
	}

	public static int getMIN_POOL_SIZE() {
		return MIN_POOL_SIZE;
	}

	public static int getMAX_POOL_SIZE() {
		return MAX_POOL_SIZE;
	}

	public static int getMAX_STATEMENTS() {
		return MAX_STATEMENTS;
	}

	public static int getIDLE_TIMEOUT() {
		return IDLE_TIMEOUT;
	}
}
