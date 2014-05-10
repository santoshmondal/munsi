package com.async.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtil {
	private static final Logger LOG = Logger.getLogger(CommonUtil.class);
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

	public static void smsMsg(String toNo, String msg) {
		String strURL = "http://api.mVaayoo.com/mvaayooapi/MessageCompose?user=padiyodi@gmail.com:isdc@1234&"
				+ "senderID=TEST SMS&"
				+ "receipientno="
				+ toNo
				+ "&dcs=0&msgtxt="
				+ msg
				+ "&state=4"; 
		
		System.out.println(" URL is :" + strURL);
		try {
			java.net.URL obj = new java.net.URL(strURL);
			HttpURLConnection httpReq = (HttpURLConnection) obj.openConnection();
			httpReq.setDoOutput(true);
			httpReq.setInstanceFollowRedirects(true);
			httpReq.setRequestMethod("GET");
			int iStatus = httpReq.getResponseCode();
			System.out.println("Status is:" + iStatus);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
