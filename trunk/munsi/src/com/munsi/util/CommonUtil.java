package com.munsi.util;

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

public class CommonUtil {
	private static final Logger LOG = Logger.getLogger( CommonUtil.class );
	
	private static Map<String,String> locationMap = new LinkedHashMap<>();
	
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
}
