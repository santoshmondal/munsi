package com.estudio.util.web;

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
    	System.out.println(" Context Initialize ");
    	
    	if( Global.isLicenseExpired() ){
    		
    		Global.isStartupError = true;
    		Global.isLicenseValid = false;
    		Global.isLicenseFileFound = false;
    		
    	}
    	else{
    		
    		Global.isStartupError = false;
    		Global.isLicenseValid = true;
    		Global.isLicenseFileFound = true;
    		
    	}
		
    }
   

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    	
    }    
    
}
