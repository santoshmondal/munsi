package com.isdc.simulations;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class HelloVivek
 */
@WebServlet(name = "HelloEmbed", urlPatterns = { "/hello.action" })
public class HelloVivek extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(HelloVivek.class);
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessWithException(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessWithException(request, response);
	}

	private void doProcessWithException(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		try{
			doProcess(request, response);
		} catch(RuntimeException me) {
			LOG.error(me);
		} catch(Exception e){
			LOG.error(e);
		}
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestedPage = request.getParameter("reqPage");
		
		request.setAttribute("treq", "ihateyou");
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/test/"+requestedPage);
		rd.include(request, response);
		return;
		
	}

}
