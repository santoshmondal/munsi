package com.async.util;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtil {
	private static final Logger LOG = Logger.getLogger( CommonUtil.class );
	private static final ObjectMapper mapper = new ObjectMapper();
	static {
		
		// JSON Ignore Null, and Ignore Unknow fields
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.setSerializationInclusion(Include.NON_EMPTY);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
	}
	
	public static Object jsonToObject(String json, String fullyQualifiedClassName) {

        try {
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
		
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
 
        return null;
	}

	
	public static String generateInvoiceNumber() {
		return "";
	}
	

}
