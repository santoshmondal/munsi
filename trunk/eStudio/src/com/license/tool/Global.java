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
	private static String LIC_DATE_FORMAT = LicenseUtil.LIC_DATE_FORMAT;
	private static String licenseFilePath;

	private static Boolean validMachine = Boolean.FALSE;
	private static Boolean licenseExpired = Boolean.TRUE;
	private static Boolean licenseFileFound = Boolean.FALSE;
	private static Boolean systemDateValid = Boolean.FALSE;
	private static License license = null;

	public static void initializeLicense(String licenseFile) {
		LOG.setLevel(Level.INFO);
		File file = new File(licenseFile);

		if (!file.exists()) {
			validMachine = Boolean.FALSE;
			licenseFileFound = Boolean.FALSE;
			LOG.info("License file not found");
			return;
		}

		licenseFilePath = licenseFile;

		licenseFileFound = Boolean.TRUE;
		License tempLicense = LicenseUtil.readLicenseFile(file);
		if (tempLicense == null) {
			validMachine = Boolean.FALSE;
			LOG.info("License instance is null after reading the License.lic file");
			return;
		}
		license = tempLicense;

		if (license.isIgnoreEndDate()) {
			licenseExpired = Boolean.FALSE;
			systemDateValid = Boolean.TRUE;
		}
		else {
			licenseExpired = checkLicenseExpire();
			systemDateValid = validateSyatemDate();
		}

		if (license.isIgnoreMachine()) {
			validMachine = Boolean.TRUE;
		}
		else {
			validMachine = validateMachine();
		}
	}

	public static Boolean isLicenseExpired() {
		return licenseExpired;
	}

	public static Boolean isValidSyatemDate() {
		return systemDateValid;
	}

	public static Boolean isValidMachine() {
		return validMachine;
	}

	public static Boolean isLicenseFileExist() {
		return licenseFileFound;
	}

	public static License getLicense() {
		return license;
	}

	// === CORE METHODS === //
	private static Boolean checkLicenseExpire() {
		Date today = new Date();
		Date endDate = getLicenseEndDate();
		LOG.info("License exp date :" + endDate + ", Current date: " + today);
		if (endDate.before(today)) {
			LOG.info("License is expired");
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	private static Boolean validateSyatemDate() {
		Date lastAccessDate = getLastAccessDate();
		Date today = new Date();
		LOG.info("Last access date :" + lastAccessDate + ", Current date: " + today);
		if (lastAccessDate.after(today)) {
			LOG.info("System current date is less than last access date");
			return overrideFromNetwork();
		}
		else {
			SimpleDateFormat sdf = new SimpleDateFormat(LIC_DATE_FORMAT);
			String strTodayDate = sdf.format(today);
			license.setLastAccessDate(strTodayDate);
			return Boolean.TRUE;
		}
	}

	private static Boolean validateMachine() {

		String licBaseboardNo = license.getBaseboardSerialNuber();
		String licProcessorId = license.getCpuProcessorId();
		String licOSSerialNo = license.getOsSerialNumber();
		String licBiosSerialNo = license.getBiosSerialNuber();

		String licSid = license.getMachineSid();
		String licMachineName = license.getMachineSid();
		String licHostName = license.getMachineSid();
		String osName = System.getProperty("os.name");

		Boolean valid1 = Boolean.FALSE;
		Boolean valid2 = Boolean.FALSE;

		if (osName.contains("Windows")) {
			if (licSid != null && licSid.trim().length() > 0) {
				String sysSid = CommandUtil.executeCommand("wmic useraccount get sid /Value");
				valid1 = matchSysProperty(sysSid, licSid);
			}
			if (!valid1) {
				if (licMachineName != null && licMachineName.trim().length() > 0) {
					String sysMachineName = CommandUtil.executeCommand("wmic useraccount get domain /Value");
					valid1 = matchSysProperty(sysMachineName, licMachineName);
				}
			}
			if (!valid1) {
				if (licHostName != null && licHostName.trim().length() > 0) {
					String sysHostName = CommandUtil.executeCommand("hostname");
					valid1 = matchSysProperty(sysHostName, licHostName);
				}
			}

			if (valid1) {
				if (licBaseboardNo != null && licBaseboardNo.trim().length() > 0) {
					String sysBaseBoardNo = CommandUtil.executeCommand("wmic baseboard get serialNumber /Value");
					valid2 = matchSysProperty(sysBaseBoardNo, licBaseboardNo);
				}
				if (!valid2) {
					if (licOSSerialNo != null && licOSSerialNo.trim().length() > 0) {
						String sysOsSerialNo = CommandUtil.executeCommand("wmic os get serialNumber /Value");
						valid2 = matchSysProperty(sysOsSerialNo, licOSSerialNo);
					}
				}
				if (!valid2) {
					if (licBiosSerialNo != null && licBiosSerialNo.trim().length() > 0) {
						String sysBiosSerialNo = CommandUtil.executeCommand("wmic bios get serialNumber /Value");
						valid2 = matchSysProperty(sysBiosSerialNo, licBiosSerialNo);
					}
				}
				if (!valid2) {
					if (licProcessorId != null && licProcessorId.trim().length() > 0) {
						String sysProcesseorId = CommandUtil.executeCommand("wmic cpu get processorId /Value");
						valid2 = matchSysProperty(sysProcesseorId, licProcessorId);
					}
				}
			}
		}

		return valid1 && valid2;
	}

	private static Boolean matchSysProperty(String string1, String string2) {
		return string1.toLowerCase().contains(string2.toLowerCase());
	}

	public static Boolean overrideFromNetwork() {
		Date network = getNetworkDate();
		try {
			if (network != null && network.before(getLicenseEndDate())) {
				SimpleDateFormat sdf = new SimpleDateFormat(LIC_DATE_FORMAT);
				String date = sdf.format(network);
				license.setLastAccessDate(date);
				systemDateValid = Boolean.TRUE;
				licenseExpired = Boolean.FALSE;
				return Boolean.TRUE;
			}
		} catch (Exception exception) {
			//
		}
		return Boolean.FALSE;
	}

	private static Date getNetworkDate() {
		URL url = null;
		try {
			url = new URL("http://www.google.co.in");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			try {
				conn.connect();
			} catch (IOException exception) {
			}
			Date networkDate = new Date(conn.getDate());
			conn.disconnect();

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(networkDate);
			calendar.set(Calendar.HOUR_OF_DAY, 00);
			calendar.set(Calendar.MINUTE, 00);
			calendar.set(Calendar.SECOND, 00);
			return calendar.getTime();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOG.info("Not able to fetch date from network");
		return null;
	}

	public static void updateLastAccessTime() {
		if (license.isIgnoreEndDate()) {
			return;
		}

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (Boolean.TRUE) {
					if (isValidSyatemDate()) {
						LicenseUtil.generateLicenseFile(licenseFilePath, license);
					}
					try {
						// h * mm * sec * mili sec
						Thread.sleep(2 * 60 * 60 * 1000);
						initializeLicense(licenseFilePath);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

	private static Date getLicenseEndDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(LIC_DATE_FORMAT);
		try {
			Date date = sdf.parse(license.getEndDate());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			return calendar.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Date getLastAccessDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(LIC_DATE_FORMAT);
		if (license.getLastAccessDate() == null || license.getLastAccessDate().trim().length() == 0) {
			license.setLastAccessDate(license.getStartDate());
		}
		try {
			Date date = sdf.parse(license.getLastAccessDate());
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
}
