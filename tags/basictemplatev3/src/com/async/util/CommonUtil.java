package com.async.util;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.async.util.Constants.DBCollectionEnum;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryOperators;
import com.mongodb.util.JSON;

public class CommonUtil {
	private static final Logger LOG = Logger.getLogger( CommonUtil.class );

	private static Map<String,String> vatTypemap = new LinkedHashMap<>();
	private static Map<String,String> locationMap = new LinkedHashMap<>();
	private static Map<String,String> schemeOnMap = new LinkedHashMap<>();
	private static Map<String,String> schemeTypeMap = new LinkedHashMap<>();
	
	static {
		// Put value in location map
		locationMap.put("TE+", "TE+ Trading Expense +ve");
		locationMap.put("TE-", "TE- Trading Expense -ve");
		locationMap.put("TI+", "TI+ Trading Income +ve");
		locationMap.put("TI-", "TI- Trading Income -ve");
		locationMap.put("PE+", "PE+ Profit Losss Expense +ve");
		locationMap.put("PI+", "PI+ Profit Losss Income +ve");
		locationMap.put("BA+", "BA+ Balance Sheet Asset +ve");
		locationMap.put("BA-", "BA- Balance Sheet Asset -ve");
		locationMap.put("BL+", "BL+ Balance Sheet Lblty +ve");
		locationMap.put("BL-", "BL- Balance Sheet Lblty -ve");
		
		// Put value in vat type map
		vatTypemap.put("1", Config.getProperty("vatType.1"));
		vatTypemap.put("2", Config.getProperty("vatType.2"));
		vatTypemap.put("3", Config.getProperty("vatType.3"));
		vatTypemap.put("4", Config.getProperty("vatType.4"));

		// Put value in scheme type map
		schemeTypeMap.put("1", Config.getProperty("schemeType.1"));
		schemeTypeMap.put("2", Config.getProperty("schemeType.2"));
		
		// Put value in scheme on map
		schemeOnMap.put("1", Config.getProperty("schemeOn.1"));
		schemeOnMap.put("2", Config.getProperty("schemeOn.2"));
		
	}
	
	public static Object jsonToObject(String json, String fullyQualifiedClassName) {
        ObjectMapper mapper = new ObjectMapper();

        try {
    		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    	    Class<?> jsonClass = Class.forName(fullyQualifiedClassName);
            return mapper.readValue(json, jsonClass);
        } catch (JsonGenerationException e) {
                LOG.error(e);
        } catch (JsonMappingException e) {
                LOG.error(e);
        } catch (IOException e) {
                LOG.error(e);
        } catch (ClassNotFoundException e) {
                LOG.error(e);
        }
        return null;
	}

	public static String objectToJson(Object object) {
		
		ObjectMapper mapper = new ObjectMapper();
		
        try {
        	mapper.setSerializationInclusion(Inclusion.NON_NULL);
        	mapper.setSerializationInclusion(Inclusion.NON_EMPTY);
        	
                return mapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
                LOG.error(e);
        } catch (JsonMappingException e) {
                LOG.error(e);
        } catch (IOException e) {
                LOG.error(e);
        }

        return null;
	}
	
	public static String generateInvoiceNumber() {
		return "";
	}
	
	
	
	public static String getVatTypeJSON(){
		
		
		StringBuffer sb = new StringBuffer();
		String separater = "";
		
		for( Entry<String, String> entry : vatTypemap.entrySet() ){
			sb.append(separater);
			sb.append(entry.getKey());
			sb.append(":");
			sb.append(entry.getValue());
			separater= ";";
		}
		
	    return sb.toString();
	}
	
	
	
	public static String getSchemeTypeJSON(){
		
		StringBuffer sb = new StringBuffer();
		String separater = "";
		
		for( Entry<String, String> entry : schemeTypeMap.entrySet() ){
			sb.append(separater);
			sb.append(entry.getKey());
			sb.append(":");
			sb.append(entry.getValue());
			separater= ";";
		}
		
		return sb.toString();
	}
	
	public static String getSchemeONJSON(){
		

		StringBuffer sb = new StringBuffer();
		String separater = "";
		
		for( Entry<String, String> entry : schemeOnMap.entrySet() ){
			sb.append(separater);
			sb.append(entry.getKey());
			sb.append(":");
			sb.append(entry.getValue());
			separater= ";";
		}
		
		return sb.toString();
	}
	
	public static String getLocationString(){
		StringBuffer sb = new StringBuffer();
		String separater = "";
		
		for( Entry<String, String> entry : locationMap.entrySet() ){
			sb.append(separater);
			sb.append(entry.getKey());
			sb.append(":");
			sb.append(entry.getValue());
			separater= ";";
		}
	    
		return sb.toString();
	}
	
	public static String getLocationValue(String locCode){
		return locationMap.get(locCode);
	}
	
	
	public static String getIdNameString(DBCollectionEnum dbCollectionEnum, String valueKey, String lableKey){
		return getIdNameString(dbCollectionEnum, valueKey, lableKey, null);
	}
	
	/**
	 * @param dbCollectionEnum collection name
	 * @param valueKey 
	 * @param lableKey
	 * @param (Optional) jsonQuery : Condition string in json format of Mongo DB
	 * @return
	 */
	public static String getIdNameString(DBCollectionEnum dbCollectionEnum, String valueKey, String lableKey, String jsonQuery){
		try{
			DB mongoDB = MongoUtil.getDB();
			
			DBCollection collection = mongoDB.getCollection(dbCollectionEnum.toString() );
			DBObject dbKey = new BasicDBObject(valueKey,1).append(lableKey, 1);

			DBObject deletedQuery = MongoUtil.getQueryToCheckDeleted();
			DBObject finalQuery = deletedQuery; 
			
			if( jsonQuery != null && jsonQuery.length() > 0){
				DBObject optionalQuery = (DBObject) JSON.parse(jsonQuery);
				BasicDBList queryList = new BasicDBList();
				queryList.add(deletedQuery);
				queryList.add(optionalQuery);
				finalQuery = new BasicDBObject(QueryOperators.AND, queryList);
			}
			
			DBCursor dbCursor = collection.find(finalQuery, dbKey);
			
			StringBuffer sb = new StringBuffer();
			String separater = "";
			while ( dbCursor.hasNext() ) {
				
				BasicDBObject dbObject = (BasicDBObject) dbCursor.next();
				String value = dbObject.getString(valueKey);
				String name = dbObject.getString(lableKey);
				sb.append(separater);
				sb.append(value);
				sb.append(":");
				sb.append(name);
				separater = ";";
			}
				
			return sb.toString();
				
			}catch( Exception exception ){
				exception.printStackTrace();
			}

		return "";
	}
	

	public static String getIdLabelJSON(DBCollectionEnum dbCollectionEnum, String idKey, String lableKey, String jsonQuery){
		try{
			DB mongoDB = MongoUtil.getDB();
			
			DBCollection collection = mongoDB.getCollection(dbCollectionEnum.toString() );
			DBObject dbKey = new BasicDBObject(idKey,1).append(lableKey, 1);

			DBObject deletedQuery = MongoUtil.getQueryToCheckDeleted();
			DBObject finalQuery = deletedQuery; 
			
			if( jsonQuery != null && jsonQuery.length() > 0){
				DBObject optionalQuery = (DBObject) JSON.parse(jsonQuery);
				BasicDBList queryList = new BasicDBList();
				queryList.add(deletedQuery);
				queryList.add(optionalQuery);
				finalQuery = new BasicDBObject(QueryOperators.AND, queryList);
			}
			
			DBCursor dbCursor = collection.find(finalQuery, dbKey);
			
			String sb = "[]";
			if( dbCursor.hasNext() ) {
				
				sb=JSON.serialize(dbCursor).toString();
			}
				
			return sb;
				
			}catch( Exception exception ){
				exception.printStackTrace();
			}

		return "";
	}
}
