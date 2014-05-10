package com.estudio.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.async.util.PortalUtil;

/**
 * Servlet Filter implementation class CheckSessionFilter
 */
@WebFilter("/")
public class CheckSessionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CheckSessionFilter() {
        
    }

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		System.out.println("***** filter *****");
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		System.out.println( request.getRequestURI() );
		
		if ( !request.getRequestURI().endsWith("login") ){
			
			Boolean isValid = PortalUtil.isValidSession( request.getSession() );
			if( !isValid ){
				response.sendRedirect("");
				return;
			}
		}
		chain.doFilter(request, response);
	}



	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

}
