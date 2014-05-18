package com.estudio.util.web;

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
import javax.servlet.http.HttpSession;

import com.async.util.Config;
import com.license.tool.Global;

/**
 * Servlet Filter implementation class ValidationFilter
 */
@WebFilter(urlPatterns = {"/login.set", "/home.jsp", "/hometmp.jsp", "*.do"})
public class ValidationFilter implements Filter {



	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if ( !Global.isValidLicense() ) {
			HttpSession session = request.getSession();
			session.invalidate();
			session = request.getSession(true);
			session.setAttribute("SERVER_MESSAGE", Config.getProperty("license.invalid"));
			response.sendRedirect("index.jsp");
			return;
		}
		
		if (Global.isLicenseExpired()) {
			HttpSession session = request.getSession();
			session.invalidate();
			session = request.getSession(true);
			session.setAttribute("SERVER_MESSAGE", Config.getProperty("license.expired"));
			response.sendRedirect("index.jsp");
			return;
		}

		
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}


}
