package com.munsi.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import com.munsi.ws.NotificationUtil;

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
		try {
			NotificationUtil.initNotification();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
