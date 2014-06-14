package com.munsi.util;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Config {
	private static final Logger LOG = Logger.getLogger(Config.class);
	private static final String CONFIG_FILE_NAME = "config.properties";
	private static Properties properties = null;

	static {
		properties = new Properties();
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream inputStream = loader.getResourceAsStream(File.separator+CONFIG_FILE_NAME);
			properties.load(inputStream);
			//String currentClasspath = getCurrentClasspath(); 
			//String fullConfigFilePath = currentClasspath+File.separator+CONFIG_FILE_NAME;
			//properties.load(new FileInputStream( new File(fullConfigFilePath) ) );
		}
		catch (Exception e) {
			LOG.error(e);
		}
	}

	public static String getProperty(String key) {
		try {
			return properties.getProperty(key);
		} catch (Exception e) {
			LOG.error(e);
		}
		return null;
	}

	public static String getCurrentClasspath() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL resource = loader.getResource("");
		LOG.info(resource.getPath());
		return resource.getPath();
	}
}
