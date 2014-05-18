package com.estudio.util.web;

import java.io.File;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import com.async.util.Constants;
import com.license.tool.Global;

/**
 * Application Lifecycle Listener implementation class ContextListener
 * 
 */
@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(ServletContextListenerImpl.class);

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		String licenseFilePath = arg0.getServletContext().getRealPath("")
				+ File.separator + "config" + File.separator + "License.lic";

		Global.initializeLicense(licenseFilePath);

		// DB Scheduler Every 24 Hrs.
		Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {

				Calendar cal = Calendar.getInstance();
				try {
					String bFolder = Constants.DB_BACUPDIR;
					String dbName = Constants.DB_NAME;

					bFolder = bFolder + cal.get(Calendar.YEAR) + cal.get(Calendar.MONTH) + cal.get(Calendar.DATE) + "";

					File bFolderFile = new File(bFolder);
					if (!bFolderFile.exists()) {

						bFolderFile.mkdirs();

						ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start", "mongodump.exe", "--db", dbName, "--out", bFolder);
						Process p = pb.start();

						p.waitFor();
						p.destroy();
					}

					LOG.info("MONGO BACKUP SCHEDULER RAN SUCCESSFULLY :: FOLDER CREATED " + bFolder);
				} catch (Exception e) {
					LOG.error("MONGO BACKUP SCHEDULER RAN INTO EXCEPTION :: ", e);
				}
			}
		}, 0, 24, TimeUnit.HOURS);
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
