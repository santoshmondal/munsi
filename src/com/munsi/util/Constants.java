package com.munsi.util;

public class Constants {
	
	public static final String DB_NAME = Config.getProperty("db.name");
	public static final String DB_HOST = Config.getProperty("db.host");
	public static final String DB_PORT_STRRING = Config.getProperty("db.port");
	public static final Integer DB_PORT;
	
	static{
		DB_PORT = Integer.parseInt(DB_PORT_STRRING);
	}
	
	

	
}
