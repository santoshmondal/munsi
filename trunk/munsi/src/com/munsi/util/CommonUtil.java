package com.munsi.util;

import java.io.IOException;
import java.util.Random;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class CommonUtil {
	private static final Logger LOG = Logger.getLogger( CommonUtil.class );
	
	public static Object jsonToObject(String json, String fullyQualifiedClassName) {
        ObjectMapper mapper = new ObjectMapper();

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
		ObjectMapper mapper = new ObjectMapper();

        try {
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
	
	
}
