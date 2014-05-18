package com.license.tool;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class Global {
	public static Logger LOG = Logger.getLogger(Global.class); 
	private static String LIC_DATE_FORMAT = "dd-MM-yyyy";
	private static String licenseFilePath ;
	
	private static Boolean licenseValid = false;
	private static Boolean licenseExpired = true;
	private static Boolean licenseFileFound = false;
	private static Boolean systemDateValid = false;
	private static License license = null;

	/*
	public static Boolean isLicenseExpired(){
		
		Calendar cal = Calendar.getInstance();
    	cal.set(2014, Calendar.JUNE, 15, 23, 59, 59);
    	Date endDate = cal.getTime();
    	Date today = new Date();
    	
    	if( endDate.before( today ) ){
    		return true;
    	}
    	
    	return false;
	}*/
	
	public static void initializeLicense( String licenseFile ){
		LOG.setLevel( Level.INFO );
		File file = new File( licenseFile );
		
		if ( !file.exists() ) {
			licenseValid = false;
			licenseFileFound = false;
			LOG.info("License file not found");
			return ;
		}
		
		licenseFilePath = licenseFile;
		
		licenseFileFound = true;
		License templicense = LicenseUtil.readLicenseFile(file);
		if( templicense == null ){
			licenseValid = false;
			LOG.info("License instance is null after reading the lic file");
			return;
		}
		license = templicense;
		licenseExpired = checkLicenseExpire();
		systemDateValid = validateSyatemDate();
		licenseValid = validateMachine();
		
	}

	public static Boolean isLicenseExpired(){
		return licenseExpired;
	}

	public static Boolean isValidSyatemDate(){
		return systemDateValid;
	}
	
	public static Boolean isValidLicense(){
		return licenseValid;
	}

	public static Boolean isLicenseFileExist(){
		return licenseFileFound;
	}
	
	public static License getLicense() {
		return license;
	}
	
	// === CORE METHODS === //
	private static Boolean checkLicenseExpire(){
		Date today = new Date();
		Date endDate = getLicenseEndDate();
		LOG.info("License exp date :"+ endDate +", Current date: "+today);
		if( endDate.before( today ) ){
			return true;
		}
		LOG.info("License is expired");
		return false;
	}
	
	private static Boolean validateSyatemDate(){
		Date lastAccessDate = getLastAccessDate();
		Date today = new Date();
		LOG.info("Last access date :"+ lastAccessDate +", Current date: "+today);
		if( lastAccessDate.after( today ) ){
			LOG.info("System current date is less than last access date");
			return overrideFromNetwork();
		}
		
		return  true;
	}
	
	private static Boolean validateMachine(){
		
		String licBaseboardNo = license.getBaseboardSerialNuber();
		String licProcessorId = license.getCpuProcessorId();
		String licOSSerialNo = license.getOsSerialNumber();
		String licBiosSerialNo = license.getBiosSerialNuber();
		
		String licSid = license.getMachineSid();
		String licMachineName = license.getMachineSid();
		String licHostName = license.getMachineSid();
		String osName = System.getProperty("os.name");
		
		Boolean valid1 = false;
		Boolean valid2 = false;
		
		if( osName.contains("Windows") ){
			if( licSid != null && licSid.trim().length() > 0 ){
				String sysSid = CommandUtil.executeCommand("wmic useraccount get sid /Value");
				valid1 = matchSysProperty(sysSid, licSid);
			}
			if( !valid1 ){
				if( licMachineName != null && licMachineName.trim().length() > 0 ){
					String sysMachineName = CommandUtil.executeCommand("wmic useraccount get domain /Value");
					valid1 =  matchSysProperty(sysMachineName, licMachineName);
				}
			}
			if( !valid1 ){
				String sysHostName = CommandUtil.executeCommand("hostname");
				valid1 = matchSysProperty(sysHostName, licHostName);
			}
			
			if ( valid1 ){
				if ( licBaseboardNo != null && licBaseboardNo.trim().length() >0 ){
					String sysBaseBoardNo = CommandUtil.executeCommand("wmic baseboard get serialNumber /Value");
					valid2 = matchSysProperty(sysBaseBoardNo, licBaseboardNo);
				}
				if( !valid2 ){
					if ( licProcessorId != null && licProcessorId.trim().length() >0 ){
						String sysProcesseorId = CommandUtil.executeCommand("wmic cpu get processorId /Value");
						valid2 = matchSysProperty(sysProcesseorId, licProcessorId);
					}
				}
				if( !valid2 ){
					if ( licOSSerialNo != null && licOSSerialNo.trim().length() >0 ){
						String sysOsSerialNo = CommandUtil.executeCommand("wmic os get serialNumber /Value");
						valid2 = matchSysProperty(sysOsSerialNo,licOSSerialNo);
					}
				}
				if( !valid2 ){
					if(licBiosSerialNo != null && licBiosSerialNo.trim().length() > 0){
						String sysBiosSerialNo = CommandUtil.executeCommand("wmic bios get serialNumber /Value");
						valid2 = matchSysProperty(sysBiosSerialNo,licBiosSerialNo);
					}
				}
			}
		}
		
		return ( valid1 && valid2 );
	}
	
	private static Boolean matchSysProperty(String string1, String string2){
		return string1.toLowerCase().contains( string2.toLowerCase() );
	}
	
	public static boolean overrideFromNetwork(){
		Date network = getNetworkDate();
		try{
			if ( network != null && network.before( getLicenseEndDate() ) ){
				SimpleDateFormat sdf = new SimpleDateFormat( LIC_DATE_FORMAT );
				String date = sdf.format(network);
				license.setLastAccessDate( date );
				systemDateValid = true;
				licenseExpired = false;
				return true;
			}
		}
		catch(Exception exception){
			//
		}
		return false;
	}
	
	private static Date getNetworkDate(){
		URL url;
		try {
			url = new URL("http://www.google.co.in" );
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			Date networkDate = new Date( conn.getDate() );
			conn.disconnect();
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(networkDate);
			calendar.set(Calendar.HOUR_OF_DAY, 00);
			calendar.set(Calendar.MINUTE, 00);
			calendar.set(Calendar.SECOND, 00);
			return calendar.getTime();
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void updateLastAccessTime(){
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while ( true ) {
					if( isValidSyatemDate() ){
						LicenseUtil.generateLicenseFile(licenseFilePath, license);
					}
					try { 
						Thread.sleep( 60 * 60 * 1000 );
						initializeLicense( licenseFilePath );
					}
					catch (InterruptedException e) { 
						e.printStackTrace(); 
					}
				}
			}
		});
		t.start();
	}
	
	private static Date getLicenseEndDate(){
		SimpleDateFormat sdf = new SimpleDateFormat( LIC_DATE_FORMAT );
		try {
			Date date = sdf.parse( license.getEndDate() );
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			return calendar.getTime();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Date getLastAccessDate() {
		SimpleDateFormat sdf = new SimpleDateFormat( LIC_DATE_FORMAT );
		if( license.getLastAccessDate()==null || license.getLastAccessDate().trim().length() > 0 ){
			license.setLastAccessDate( license.getStartDate() );
		}
		try {
			
			Date date = sdf.parse( license.getLastAccessDate() );
			return date;
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	
	/*
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat( LIC_DATE_FORMAT);
		try {
			Date date = sdf.parse( "15-08-2014" );
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			System.out.println( date );
			System.out.println( calendar.getTime() );
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			System.out.println(calendar.getTime());
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}*/
}
