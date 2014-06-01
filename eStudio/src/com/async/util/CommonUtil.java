package com.async.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtil {
	private static final Logger LOG = Logger.getLogger(CommonUtil.class);
	private static final Logger SMS_LOG = Logger.getLogger("SMS_LOGGER");
	private static final ObjectMapper mapper = new ObjectMapper();
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	static {

		// JSON Ignore Null, and Ignore Unknow fields
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.setSerializationInclusion(Include.NON_EMPTY);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);

	}

	public static Object jsonToObject(String json,
			String fullyQualifiedClassName) {

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

	public static String longToStringDate(Long longValue) {
		return longToStringDate(longValue, DATE_FORMAT);
	}

	public static String longToStringDate(Long longValue, String format) {
		String strDate = "";
		try {
			Date d = null;
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(longValue);
			d = cal.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			strDate = sdf.format(d);
		} catch (Exception e) {

		}

		return strDate;
	}

	public static Date stringToDate(String string) {
		return stringToDate(string, DATE_FORMAT);
	}

	public static Date stringToDate(String string, String format) {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(string);
		} catch (Exception e) {
			return null;
		}
	}

	public static void smsMsg(String toNo, String msg)	throws UnsupportedEncodingException {
		SMS_LOG.info("SMS :: [smsMsg] > Mobile:"+toNo +" | Text :"+msg);
		String strURL = "http://api.mVaayoo.com/mvaayooapi/MessageCompose?user=padiyodi@gmail.com:isdc@1234&"
				+ "senderID="
				+ java.net.URLEncoder.encode("ISDCSM", "UTF-8")
				+ "&receipientno="
				+ toNo
				+ "&dcs=0&msgtxt="
				+ java.net.URLEncoder.encode(msg, "UTF-8") + "&state=4";

		SMS_LOG.debug("SMS :: [smsMsg] > URL :"+ strURL);
		try {
			SMS_LOG.debug("SMS :: [smsMsg] > Start URL Connection:"+ new  Date());
			java.net.URL obj = new java.net.URL(strURL);
			HttpURLConnection httpReq = (HttpURLConnection) obj
					.openConnection();
			httpReq.setDoOutput(true);
			httpReq.setInstanceFollowRedirects(true);
			httpReq.setRequestMethod("GET");
			String iStatus = httpReq.getResponseMessage();
			SMS_LOG.debug("SMS :: [smsMsg] > End URL Connection:"+ new  Date());
			SMS_LOG.debug("SMS :: [smsMsg] > Status: " + iStatus);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
			SMS_LOG.error("SMS :: [smsMsg] > " + ex.getMessage());
		} catch (IOException ex) {
			ex.printStackTrace();
			SMS_LOG.error("SMS :: [smsMsg] > " + ex.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			SMS_LOG.error("SMS :: [smsMsg] > " + e.getMessage());
		}
	}

}
