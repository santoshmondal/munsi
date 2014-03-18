package com.munsi.util;

public class Constants {

	public static final String DB_NAME = Config.getProperty("db.name");
	public static final String DB_HOST = Config.getProperty("db.host");
	public static final String DB_PORT_STRRING = Config.getProperty("db.port");
	public static final Integer DB_PORT;

	static {
		DB_PORT = Integer.parseInt(DB_PORT_STRRING);
	}

	public enum DBCollectionEnum {
		// @formatter:off
		MAST_MAIN_ACCOUNT("mast_main_account"),
		MAST_CUSTOMER("mast_customer"),
		MAST_AREA("mast_area"),
		MAST_BEAT("mast_beat"),
		MAST_MANUFACTURER("mast_manufacturer"),
		MAST_ACCESS_USER("mast_access_user");
		// @formatter:on

		private final String collectionName;

		DBCollectionEnum(String collectionName) {
			this.collectionName = collectionName;
		}

		@Override
		public String toString() {
			return collectionName;
		}
	}

}
