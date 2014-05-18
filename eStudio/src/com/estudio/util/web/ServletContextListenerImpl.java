package com.estudio.util.web;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.license.tool.Global;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    /** name = new (arguments);
     * Default constructor. 
     */
    public ServletContextListenerImpl() {
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	
    	String licenseFilePath = arg0.getServletContext().getRealPath("")
				+ File.separator + "config" + File.separator + "License.lic";
    	
		Global.initializeLicense( licenseFilePath );
		
    }
   

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    	
    }    
    
}
