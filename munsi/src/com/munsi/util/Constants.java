package com.munsi.util;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Constants {

	public static final String DB_NAME = Config.getProperty("db.name");
	public static final String DB_HOST = Config.getProperty("db.host");
	public static final String DB_PORT_STRRING = Config.getProperty("db.port");
	public static final Integer DB_PORT;
	
	static {
		DB_PORT = Integer.parseInt(DB_PORT_STRRING);
	}
	
	public static final String OPERATION = "op";
	
	public enum DBCollectionEnum {
		// @formatter:off
		MAST_MAIN_ACCOUNT("mast_main_account"),
		MAST_CUSTOMER("mast_customer"),
		MAST_AREA("mast_area"),
		MAST_BEAT("mast_beat"),
		MAST_MANUFACTURER("mast_manufacturer"),
		MAST_ACCESS_USER("mast_access_user"),
		MAST_TAX("mast_tax"),
		MAST_PRODUCT_GROUP("mast_product_group"), 
		MAST_PRODUCT("mast_product");
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
		VIEW,
		VIEW_ALL,
		ADD,
		EDIT,
		DELETE
		// @formatter:on

	}
	
	public static String getVatTypeJSON(){
		Map<String,String> map = new LinkedHashMap<>();
		map.put("1", Config.getProperty("vatType.1"));
		map.put("2", Config.getProperty("vatType.2"));
		map.put("3", Config.getProperty("vatType.3"));
		map.put("4", Config.getProperty("vatType.4"));
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			return mapper.writeValueAsString(map);
        }
		catch (JsonGenerationException e) {}
		catch (JsonMappingException e) {}
		catch (IOException e) {}
		
	    return "";
	}
	
	public static String getSchemeTypeJSON(){
		Map<String,String> map = new LinkedHashMap<>();
		map.put("1", Config.getProperty("schemeType.1"));
		map.put("2", Config.getProperty("schemeType.2"));
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(map);
        }
		catch (JsonGenerationException e) {}
		catch (JsonMappingException e) {}
		catch (IOException e) {}
		
	    return "";
	}
	
	public static String getSchemeONJSON(){
		Map<String,String> map = new LinkedHashMap<>();
		map.put("1", Config.getProperty("schemeON.1"));
		map.put("2", Config.getProperty("schemeON.2"));
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(map);
        }
		catch (JsonGenerationException e) {}
		catch (JsonMappingException e) {}
		catch (IOException e) {}
		
	    return "";
	}
	
	
}
