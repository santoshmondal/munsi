package com.async.util;

import java.util.Calendar;
import java.util.Date;


public class Global {

	public static Boolean isLicenseValid = false;
	public static Boolean isLicenseFileFound = false;
	public static Boolean isStartupError = true;

	public static Boolean isLicenseExpired(){
		
		Calendar cal = Calendar.getInstance();
    	cal.set(2014, Calendar.JUNE, 15, 23, 59, 59);
    	Date endDate = cal.getTime();
    	Date today = new Date();
    	
    	if( endDate.before( today ) ){
    		return true;
    	}
    	
    	return false;
    	
    	
	}
}
