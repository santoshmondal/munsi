package com.async.util;

public class Constants {

	public static final String DB_NAME = Config.getProperty("db.name");
	public static final String DB_HOST = Config.getProperty("db.host");
	public static final String DB_PORT_STRRING = Config.getProperty("db.port");
	public static final String DB_BACUPDIR = Config.getProperty("db.backupdir");
	public static final Integer DB_PORT;

	static {
		// DB port
		DB_PORT = Integer.parseInt(DB_PORT_STRRING);
	}

	public static final String OPERATION = "op";
	public static final String COLLECTION_KEY = "id";
	public static final String JQGRID_EMPTY = "_empty";
	public static final String JQGRID_LEVEL = "level";

	public enum DBCollectionEnum {
		// @formatter:off
		ACCESS_USER("access_user"), CUSTOMER("customer"), MASTER("master"), INVOICE("invoice"), PAPER_PURCHASE("paper_purchase"), PAPER_INVENTORY("paper_inventory");
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

	public enum UIOperations {
		// @formatter:off
		VIEW, VIEW_ALL, ADD, EDIT, DELETE, SAVE, FETCH, RESEND_SMS;
		// @formatter:on
	}

	public enum ServiceEnum {
		// @formatter:off
		PHOTO_DETAILS, FRAME_DETAILS, LAMINATION_DETAILS;
		// @formatter:on
	}

	public enum MasterTypeEnum {
		// @formatter:off
		PHOTO("photo"), FRAME("frame"), LAMINATION("lamination");
		// @formatter:on

		private final String masterType;

		MasterTypeEnum(String masterType) {
			this.masterType = masterType;
		}

		@Override
		public String toString() {
			return masterType;
		}
	}

	public enum OrderStatuEnum {
		// @formatter:off
		RAW_DATA("Raw Data"), FINAL_DATA("Final Data"), SEND_TO_PRINT("Sent to Print"), RECEIVED_FROM_PRINT("Received from Print"), DELIVERED("Delivered to Customer");
		// @formatter:on

		private final String status;

		private OrderStatuEnum(String status) {
			this.status = status;
		}

		@Override
		public String toString() {
			return status;
		}

		public static String getEnumName(String value) {
			for (OrderStatuEnum d : OrderStatuEnum.values()) {
				if (d.toString().equals(value)) {
					return d.name();
				}
			}
			return "";
		}
	}
}
